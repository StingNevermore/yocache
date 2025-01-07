package com.nevermore.yocache.test.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;
import org.springframework.web.service.annotation.GetExchange;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SmokeTests {

    @Value("${local.server.port}")
    private int port;

    private final RestClient client;

    public SmokeTests() {
        client = RestClient.create("http://localhost:" + port);
    }

    @Test
    public void testPut() {
        System.out.println(port);
        client.put().uri("/test/a/b").body(new byte[]{0})
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(), (req, res) -> {
                    throw new RuntimeException(res.getStatusText());
                });
    }

    @Test
    @GetExchange
    public void testGet() {
        client.get().uri("/test/a/b")
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(), (req, res) -> {
                    throw new RuntimeException(res.getStatusText());
                });
    }
}
