package com.perfectmatch.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.bson.types.ObjectId;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.persistence.model.Style;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MusicControllerWebTest {


	@Autowired
    private WebTestClient webTestClient;
	
    @Test
    public void testIfServerIsOk() {
        webTestClient.get()
                .uri("/music/repo")
                .exchange()
                .expectStatus().isOk();
    }
    
    @Test
    public void testReadRepository() {
    	//given
    	//
        webTestClient.get()
                .uri("/music/repo")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Music.class)
                .hasSize(3);
                
    }
    
    @Test
    public void testReadRepositoryEntitiesContent() {
    	//given - data driven setup in the web server
        webTestClient.get()
                .uri("/music/repo")
                .exchange()
                .expectStatus().isOk()
		        .expectBody()
		        .jsonPath("$[0].id").isNotEmpty()
		        .jsonPath("$[0].name").isNotEmpty()
		        .jsonPath("$[0].name").isEqualTo("Please Stop (Original Mix)")
		        .jsonPath("$[0].artist").isNotEmpty()
		        .jsonPath("$[0].artist").isEqualTo("Latmun")
		        .jsonPath("$[0].style").isNotEmpty()
		        .jsonPath("$[0].style").isEqualTo("TECH_HOUSE")
        
		        .jsonPath("$[1].id").isNotEmpty()
		        .jsonPath("$[1].name").isNotEmpty()
		        .jsonPath("$[1].name").isEqualTo("def (Original Mix)")
		        .jsonPath("$[1].artist").isNotEmpty()
		        .jsonPath("$[1].artist").isEqualTo("Latmun")
		        .jsonPath("$[1].style").isNotEmpty()
		        .jsonPath("$[1].style").isEqualTo("TECH_HOUSE")
		        
		        .jsonPath("$[2].id").isNotEmpty()
		        .jsonPath("$[2].name").isNotEmpty()
		        .jsonPath("$[2].name").isEqualTo("Please Stop (Original Mix)XPTO")
		        .jsonPath("$[2].artist").isNotEmpty()
		        .jsonPath("$[2].artist").isEqualTo("LatmunXPTO")
		        .jsonPath("$[2].style").isNotEmpty()
		        .jsonPath("$[2].style").isEqualTo("TECH_HOUSE");
        
 
    }
	
    
    @Test
    public void testFindByName() {
    	//given
    	//
        webTestClient.get()
                .uri("/music/{name}", "Please Stop (Original Mix)")
                .exchange()
                .expectStatus().isOk()
		        .expectBody()
		        .jsonPath("$.id").isNotEmpty()
		        .jsonPath("$.name").isNotEmpty()
		        .jsonPath("$.name").isEqualTo("Please Stop (Original Mix)")
		        .jsonPath("$.artist").isNotEmpty()
		        .jsonPath("$.artist").isEqualTo("Latmun")
		        .jsonPath("$.style").isNotEmpty()
		        .jsonPath("$.style").isEqualTo("TECH_HOUSE");
        
 
    }
    
    
    @Test
    public void testCreateMusic() {
    	//given
    	String artistName = "Latmun" ;
    	String musicName = "Please Stop (Original Mix)";
    	Music createRepoRequest = Music.ofType(Style.TECH_HOUSE)
    								.withArtist(artistName)
    								.withName(musicName)
    								.build();
	
    	//When
        webTestClient.post()
                .uri("/music/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(createRepoRequest), Music.class).exchange()
                //.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectStatus().is5xxServerError();
                //Then
//        		{
//  			  "id" : "5a58f051b361ae32c0c1e4e9",
//  			  "artist" : "Latmun",
//  			  "name" : "Please Stop (Original Mix)",
//  			  "style" : "TECH_HOUSE",
//  			  "samples" : null
//  			}                
//                .expectBody()
//                .jsonPath("$.name").isNotEmpty()
//                .jsonPath("$.name").isEqualTo(musicName)
//        		.jsonPath("$.artist").isNotEmpty()
//        		.jsonPath("$.artist").isEqualTo(artistName)
//        		.jsonPath("$.style").isNotEmpty()
//        		.jsonPath("$.style").isEqualTo(Style.TECH_HOUSE.name())
//        		.jsonPath("$.samples").doesNotExist();
        				
        

    }
    
    
}
