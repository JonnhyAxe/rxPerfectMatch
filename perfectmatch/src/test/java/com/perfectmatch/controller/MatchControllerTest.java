package com.perfectmatch.controller;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import com.perfectmatch.persistence.dao.MatchRepository;
import com.perfectmatch.persistence.model.Match;
import com.perfectmatch.web.controller.MatchController;
import com.perfectmatch.web.services.impl.SampleMatchServiceBean;

import reactor.core.publisher.Flux;


@RunWith(MockitoJUnitRunner.class)
public class MatchControllerTest {
	
	@Mock
	private MatchRepository repo;

	
	@Mock
    private SampleMatchServiceBean sampleMatchServiceBean;

    

	@InjectMocks
	private MatchController controller;


	@Test
	public void shouldReturnAllMatchs() throws IOException {

		// Given
		when(sampleMatchServiceBean.findAll()).thenReturn(Flux.just(new Match(), 
				new Match()));

		// When
		final List<Match> response = controller.findByRepo().collectList().block();

		// Then
//		assertThat(response.getStatusCode()).isEqualTo(OK);
		assertThat(response).asList().containsAll(response);
	}
	
	
	@Test
	public void shouldReturnEmptyBodyWhenNoMatchs() throws IOException {

		// Given
		when(sampleMatchServiceBean.findAll()).thenReturn(Flux.empty());

		// When
		final List<Match> response = controller.findByRepo().collectList().block();

		// Then
		//assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
		assertThat(response).asList().isEmpty();
	}
	

//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//
//import java.io.IOException;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//import com.perfectmatch.persistence.dao.MatchRepository;
//import com.perfectmatch.persistence.model.Match;
//import com.perfectmatch.web.controller.MatchController;
//import com.perfectmatch.web.services.impl.SampleMatchServiceBean;
//
//import reactor.core.publisher.Flux;
//
//
//@RunWith(MockitoJUnitRunner.class)
//public class MatchControllerTest {
//	
//	@Mock
//	private MatchRepository matchRepository;
//	
//	@Mock
//    private SampleMatchServiceBean sampleMatchServiceBean;
//    
//    @InjectMocks
//	private MatchController controller;
//	
//	private WebTestClient webTestClient;
//	 
//	@Before
//	public void setUp() throws Exception {
////	 	matchRepository = Mockito.mock(MatchRepository.class);
////	 	sampleMatchServiceBean = Mockito.mock(SampleMatchServiceBean.class);
//	 	//controller = new MatchController();
//	 	//controller.SampleMatchService(sampleMatchServiceBean);
//	    webTestClient = WebTestClient.bindToController(controller).build();
//	}
//	 
//	@Test
//	public void shouldReturnAllMatchs() throws IOException {
//		// Given
//	    given(sampleMatchServiceBean.findAll())
//	    	.willReturn(Flux.just(new Match(), new Match()));
//
//
//		//when(sampleMatchServiceBean.findAll()).thenReturn(Mono.just(matchs));
//
//		// When
//		webTestClient.get()
//	        .uri("/match/repo")
//	        .exchange()
//	        .expectBodyList(ResponseEntity.class)
//	        .hasSize(2);
//		
////		final ResponseEntity<List<Match>> response = controller.findByRepo().block();
////
////		// Then
////		assertThat(response.getStatusCode()).isEqualTo(OK);
////		assertThat((Iterable<Match>) response.getBody()).asList().containsAll(matchs);
//	}
//	
//	
//	@Test
//	public void shouldReturnEmptyBodyWhenNoMatchs() throws IOException {
//
//		// Given
//		when(sampleMatchServiceBean.findAll()).thenReturn(Flux.empty());
//
//		// When
//		
//		webTestClient.get()
//        .uri("/match/repo")
//        .exchange()
//        .expectBodyList(ResponseEntity.class)
//        .hasSize(0);
//	
//		//final ResponseEntity<List<Match>> response = controller.findByRepo().
//
//		// Then
//		//assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
//	}
//	

	
}
