package com.mak.gatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/inventoryServiceFallBack")
    public String userServiceFallBackMethod() {
        return "Inventory Service is taking longer than Expected." +
                " Please try again later";
    }
}