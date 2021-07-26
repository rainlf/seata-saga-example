package com.rainlf.inventory.service.impl;

import com.rainlf.inventory.service.InventoryAction;
import org.springframework.stereotype.Service;

/***
 * @author : Rain
 * @date : 2021/7/23 3:25 PM
 */
@Service("inventoryAction")
public class InventoryActionImpl implements InventoryAction {

    @Override
    public boolean reduce(String businessKey, int count) {
        System.out.println("reduce inventory succeed, count: " + count + ", businessKey:" + businessKey);
        return true;
    }

    @Override
    public boolean compensateReduce(String businessKey) {
        System.out.println("compensate reduce inventory succeed, businessKey:" + businessKey);
        return true;
    }
}
