package com.rainlf.balance.controller.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/***
 * @author : Rain
 * @date : 2021/7/26 4:57 PM
 */
@Data
public class CommitRequest {
    private String businessKey;
    private BigDecimal amount;
    private Map<String, Object> params;
}
