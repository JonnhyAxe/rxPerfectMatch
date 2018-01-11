package com.perfectmatch.controller;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
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
import com.perfectmatch.web.services.impl.MusicServiceBean;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(MockitoJUnitRunner.class)
public class MusicControllerTest {
	
	@Mock
    private MusicServiceBean musicService;

	@InjectMocks
	private MusicController controller;
	
//	@Before
//	public void setUp() {
//		when(musicService.getDao()).thenReturn(dao);
//	}

	
	@Test
	public void shouldReturnAllMusics() throws IOException {

		// Given
		
		final List<Music> customers = asList(
				Music.ofType(Style.TECH_HOUSE).withName("music1").build(),
				Music.ofType(Style.TECH_HOUSE).withName("music1").build());

		when(musicService.findAll()).thenReturn(Flux.fromIterable(customers));

		// When
		final List<Music> response = controller.findByRepo().collectList().block();

		// Then
		assertThat(response).asList().containsAll(customers);
	}
	
	@Test
	public void shouldReturnEmptyBodyWhenNoMusics() throws IOException {

		// Given
		when(musicService.findAll()).thenReturn(Flux.empty());

		// When
		final List<Music> response = controller.findByRepo().collectList().block();

		// Then
		assertThat(response).asList().isEmpty();
	}
	
	@Test
	public void shouldReturnOneCustomerByName() {

		String musicName = "music1";
		// Given
		final Music customer = Music.ofType(Style.TECH_HOUSE).withName(musicName).build();
		
		when(musicService.findByName(musicName)).thenReturn(Mono.just(customer));

		// When
		final  Music response  = controller.findByName(musicName).block();

		// Then
	
		assertThat(response).isEqualTo(customer);
	}
	
	@Test
	public void shouldReturn404IfMusicIsNotFound() {
		String musicName = "music1";
		// Given
		when(musicService.findByName(musicName)).thenReturn(Mono.empty());

		// When
		final Music response = controller.findByName(musicName).block();

		// Then
		assertThat(response).isNull();
	
	}
	
	
	@Test
	public void shouldAddANewMusic() throws Exception {

		// Given
		final Music newMusic = Music.ofType(Style.TECH_HOUSE).withName("music").build();

		final ObjectId id = ObjectId.get();
		ReflectionTestUtils.setField(newMusic, "id", id);

		when(musicService.findByName("music")).thenReturn(Mono.just(newMusic));
		when(musicService.create(newMusic)).thenReturn(Mono.just(newMusic));

		// When
		final ResponseEntity<?> response = controller.create(newMusic).block();

		// Then
		assertThat(response.getStatusCode()).isEqualTo(CREATED);
		assertThat(response.getHeaders().getLocation().toString()).isEqualTo(String.format("/customers/%s", id));
	}
	
	
}
