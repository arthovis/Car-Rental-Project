package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class CarRentalControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenAPostRequestReceived_thenCreateCarRentalOffice() {

        String relativePath = "/car-rental-offices";

        ResponseEntity<Long> actualResponse = this.restTemplate.postForEntity(url(relativePath), null, Long.class);

        Assertions.assertEquals(1L, actualResponse.getBody());
    }
}
