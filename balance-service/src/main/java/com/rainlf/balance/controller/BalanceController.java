package com.rainlf.balance.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.rainlf.balance.controller.dto.CommitRequest;
import com.rainlf.balance.controller.dto.CompensateRequest;
import com.rainlf.balance.service.BalanceAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/***
 * @author : Rain
 * @date : 2021/7/23 3:21 PM
 */
@Slf4j
@RestController
public class BalanceController {
    @Autowired
    private BalanceAction balanceAction;

    @PostMapping("commit")
    public boolean commit(@RequestBody CommitRequest commitRequest) {
        return balanceAction.reduce(
                commitRequest.getBusinessKey(),
                commitRequest.getAmount(),
                commitRequest.getParams()
        );
    }

    @PostMapping("compensate")
    public boolean commit(@RequestBody CompensateRequest compensateRequest) {
        return balanceAction.compensateReduce(
                compensateRequest.getBusinessKey(),
                compensateRequest.getParams()
        );
    }

    @NacosInjected
    private NamingService namingService;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public List<Instance> get() throws NacosException {
        return namingService.getAllInstances("seata-server", "SEATA_GROUP");
    }
}
