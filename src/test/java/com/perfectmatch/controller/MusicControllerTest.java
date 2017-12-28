package com.perfectmatch.controller;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.perfectmatch.persistence.dao.MusicRepository;
import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.persistence.model.Style;
import com.perfectmatch.web.controller.MusicController;

import customerservice.domain.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(MockitoJUnitRunner.class)
public class MusicControllerTest {
	
	@Mock
	private MusicRepository repo;

	@InjectMocks
	private MusicController controller;

	
	@Test
	public void shouldReturnAllMusics() throws IOException {

		// Given
		final List<Music> customers = asList(
				Music.ofType(Style.TECH_HOUSE).withName("music1").build(),
				Music.ofType(Style.TECH_HOUSE).withName("music1").build());
		when(repo.findAll()).thenReturn(Flux.fromIterable(customers));

		// When
		final ResponseEntity<List<Music>> response = controller.findByRepo().block();

		// Then
		assertThat(response.getStatusCode()).isEqualTo(OK);
		assertThat((Iterable<Music>) response.getBody()).asList().containsAll(customers);
	}
	
	@Test
	public void shouldReturnEmptyBodyWhenNoMusics() throws IOException {

		// Given
		when(repo.findAll()).thenReturn(Flux.empty());

		// When
		final ResponseEntity<List<Music>> response = controller.findByRepo().block();

		// Then
		assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
	}
	
	@Test
	public void shouldReturnOneCustomerByName() {

		String musicName = "music1";
		// Given
		final Music customer = Music.ofType(Style.TECH_HOUSE).withName(musicName).build();
		
		when(repo.findByName(musicName)).thenReturn(Flux.just(customer));

		// When
		final Music response = controller.findByName(musicName).blockFirst();

		// Then
		//assertThat(response.getStatusCode()).isEqualTo(OK);
		assertThat(response).isEqualTo(customer);
	}
	
	@Test
	public void shouldReturn404IfMusicIsNotFound() {

		// Given
		when(repo.findByName(any(String.class))).thenReturn(Flux.empty());

		// When
		final Music response = controller.findByName("Music").blockFirst();

		// Then
		//assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
		assertThat(response).isNull();
	}
	
	
	@Test
	public void shouldAddANewMusic() {

		// Given
		final Music newMusic = Music.ofType(Style.TECH_HOUSE).build();

		final ObjectId id = ObjectId.get();
		ReflectionTestUtils.setField(newMusic, "id", id);

		when(repo.existsById(any(ObjectId.class))).thenReturn(Mono.just(false));
		when(repo.save(any(Music.class))).thenReturn(Mono.just(newMusic));

		// When
		final ResponseEntity<?> response = controller.create(newMusic).block();

		// Then
		assertThat(response.getStatusCode()).isEqualTo(CREATED);
		assertThat(response.getHeaders().getLocation().toString()).isEqualTo(format("/customers/%s", id));
	}
	
	
}
