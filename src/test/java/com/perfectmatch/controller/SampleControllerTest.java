package com.perfectmatch.controller;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.perfectmatch.persistence.dao.MusicRepository;
import com.perfectmatch.persistence.dao.SampleRepository;
import com.perfectmatch.persistence.model.Sample;
import com.perfectmatch.web.controller.SampleController;
import com.perfectmatch.web.services.impl.SampleServiceBean;

import reactor.core.publisher.Flux;

@RunWith(MockitoJUnitRunner.class)
public class SampleControllerTest {


	@Mock 
	private SampleRepository sampleRepo;
	
	@Mock
    private SampleServiceBean sampleService;

	@InjectMocks
	private SampleController controller;
	
	
	@Before 
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(sampleService.getDao()).thenReturn(sampleRepo);
	}

	
	@Test
	public void shouldReturnAllSamples() throws IOException {

		// Given
		final List<Sample> customers = asList(
				new Sample(ObjectId.get(), "Sample 1", 12431235),
				new Sample(ObjectId.get(), "Sample 2", 12431235));
		
		
		when(sampleRepo.findAll()).thenReturn(Flux.fromIterable(customers));
		//when(sampleRepo.findAll()).thenReturn(Flux.just(customers));

		// When
		final ResponseEntity<List<Sample>> response = controller.findByRepo().block();

		// Then
		assertThat(response.getStatusCode()).isEqualTo(OK);
		assertThat((Iterable<Sample>) response.getBody()).asList().containsAll(customers);

	}
	
	
}
