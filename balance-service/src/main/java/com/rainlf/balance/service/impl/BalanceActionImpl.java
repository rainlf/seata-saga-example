package com.rainlf.balance.service.impl;

import com.rainlf.balance.service.BalanceAction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/***
 * @author : Rain
 * @date : 2021/7/23 3:26 PM
 */
@Service("balanceAction")
public class BalanceActionImpl implements BalanceAction {

    @Override
    public boolean reduce(String businessKey, BigDecimal amount, Map<String, Object> params) {
        if (params != null && "true".equals(params.get("throwException"))) {
            System.out.println("reduce balance failed");
            throw new RuntimeException("reduce balance failed");
        }
        System.out.println("reduce balance succeed, amount: " + amount + ", businessKey:" + businessKey);
        return true;
    }

    @Override
    public boolean compensateReduce(String businessKey, Map<String, Object> params) {
        if (params != null && "true".equals(params.get("throwException"))) {
            System.out.println("compensate reduce balance failed");
            throw new RuntimeException("compensate reduce balance failed");
        }
        System.out.println("compensate reduce balance succeed, businessKey:" + businessKey);
        return true;
    }
}