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
import org.springframework.http.ResponseEntity;

import com.perfectmatch.persistence.dao.MatchRepository;
import com.perfectmatch.persistence.model.Match;
import com.perfectmatch.web.controller.MatchController;
import com.perfectmatch.web.services.impl.SampleMatchServiceBean;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
		final List<Match> matchs = asList(
				new Match(), 
				new Match());
		when(sampleMatchServiceBean.findAll()).thenReturn(Mono.just(matchs));

		// When
		final ResponseEntity<List<Match>> response = controller.findByRepo().block();

		// Then
		assertThat(response.getStatusCode()).isEqualTo(OK);
		assertThat((Iterable<Match>) response.getBody()).asList().containsAll(matchs);
	}
	
	
	@Test
	public void shouldReturnEmptyBodyWhenNoMatchs() throws IOException {

		// Given
		when(sampleMatchServiceBean.findAll()).thenReturn(Mono.empty());

		// When
		final ResponseEntity<List<Match>> response = controller.findByRepo().block();

		// Then
		assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
	}
	

	
}
