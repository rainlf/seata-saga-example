package com.rainlf.inventory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InventoryApplication implements InitializingBean {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${seata.application-id}")
    private String applicationId;
    @Value("${seata.tx-service-group}")
    private String txServiceGroup;
    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("applicationId", applicationId);
        System.setProperty("txServiceGroup", txServiceGroup);
    }
}
