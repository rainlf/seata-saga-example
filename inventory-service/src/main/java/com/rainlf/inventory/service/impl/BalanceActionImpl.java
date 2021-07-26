package com.rainlf.inventory.service.impl;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.rainlf.inventory.service.BalanceAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/***
 * @author : Rain
 * @date : 2021/7/23 3:26 PM
 */
@Service("balanceAction")
public class BalanceActionImpl implements BalanceAction {
    @Autowired
    private RestTemplate restTemplate;

    @NacosInjected
    private NamingService namingService;

    @Value("${balance.service.name}")
    private String balanceServiceName;

    @Override
    public boolean reduce(String businessKey, BigDecimal amount, Map<String, Object> params) {
        System.out.println("ready call balance service commit");
        Map<String, Object> request = new HashMap<>();
        request.put("businessKey", businessKey);
        request.put("amount", amount);
        request.put("params", params);
        return restTemplate.postForObject(getServiceUrl() + "/commit", request, Boolean.class);
    }

    @Override
    public boolean compensateReduce(String businessKey, Map<String, Object> params) {
        System.out.println("ready call balance service compensate");
        Map<String, Object> request = new HashMap<>();
        request.put("businessKey", businessKey);
        request.put("params", params);
        return restTemplate.postForObject(getServiceUrl() + "/compensate", request, Boolean.class);
    }

    private String getServiceUrl() {
        try {
            List<Instance> instanceList = namingService.getAllInstances(balanceServiceName);
            Instance instance = instanceList.get(new Random().nextInt(instanceList.size()));
            return String.format("http://%s:%s", instance.getIp(), instance.getPort());
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}