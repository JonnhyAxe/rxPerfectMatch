package com.perfectmatch.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MatchWebClientTests {

	@Autowired
    private WebTestClient webTestClient;

	//from https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/
	//fix SSL test https://github.com/netty/netty/blob/add7efc2d4f818bfb02cef7a34c701827e83ddf7/handler-proxy/src/test/java/io/netty/handler/proxy/ProxyHandlerTest.java#L284
    @Test
    public void test5DeleteGithubRepository() {
        webTestClient.get()
                .uri("/match/repo")
                .exchange()
                .expectStatus().isOk();
    }
	
}
