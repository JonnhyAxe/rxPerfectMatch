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

import reactor.core.publisher.Flux;

@RunWith(MockitoJUnitRunner.class)
public class MatchControllerTest {
	
	@Mock
	private MatchRepository repo;

	@InjectMocks
	private MatchController controller;


	@Test
	public void shouldReturnAllMatchs() throws IOException {

		// Given
		final List<Match> matchs = asList(
				new Match(), 
				new Match());
		when(repo.findAll()).thenReturn(Flux.fromIterable(matchs));

		// When
		final ResponseEntity<List<Match>> response = controller.findByRepo().block();

		// Then
		assertThat(response.getStatusCode()).isEqualTo(OK);
		assertThat((Iterable<Match>) response.getBody()).asList().containsAll(matchs);
	}
	
	
	@Test
	public void shouldReturnEmptyBodyWhenNoMatchs() throws IOException {

		// Given
		when(repo.findAll()).thenReturn(Flux.empty());

		// When
		final ResponseEntity<List<Match>> response = controller.findByRepo().block();

		// Then
		assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
	}
	

	
}
