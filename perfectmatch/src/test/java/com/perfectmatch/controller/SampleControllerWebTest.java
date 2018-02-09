package com.perfectmatch.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.perfectmatch.persistence.model.Music;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SampleControllerWebTest {

	@Autowired
    private WebTestClient webTestClient;
	
    @Test
    public void testIfServerIsOk() {
        webTestClient.get()
                .uri("/sample/repo")
                .exchange()
                .expectStatus().isOk();
    }
    
    @Test
    public void testReadRepository() {
    	//given
    	//
        webTestClient.get()
                .uri("/sample/repo")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Music.class)
                .hasSize(1);
                
    }
    
    
    
    @Test
    public void testReadRepositoryEntitiesContent() {
    	//given - data driven setup in the web server
        webTestClient.get()
                .uri("/sample/repo")
                .exchange()
                .expectStatus().isOk()
		        .expectBody()
		        .jsonPath("$[0].id").isNotEmpty()
		        .jsonPath("$[0].name").isNotEmpty()
		        .jsonPath("$[0].name").isEqualTo("Latmun:Please Stop (Original Mix)")
		        .jsonPath("$[0].timestamp").isNotEmpty()
		        .jsonPath("$[0].timestamp").isEqualTo("180");
        
    }
	
    
	
}
