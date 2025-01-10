package com.nevermore.yocache.integration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SmokeTests {
    private final RestClient client;

    public SmokeTests(@LocalServerPort int port) {
        client = RestClient.create("http://localhost:" + port);
    }

    @Test
    @Order(1)
    public void testPut() {
        client.put().uri("/test/a/b").body(new byte[] { 0 })
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(), (req, res) -> {
                    throw new RuntimeException(res.getStatusText());
                }).toBodilessEntity();
    }

    @Test
    @Order(2)
    public void testGet() {
        long start = System.currentTimeMillis();
        client.get().uri("/test/a/b")
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(), (req, res) -> {
                    throw new RuntimeException(res.getStatusText());
                }).toEntity(byte[].class);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
