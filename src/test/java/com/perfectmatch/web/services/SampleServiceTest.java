package com.perfectmatch.web.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.perfectmatch.persistence.dao.SampleRepository;
import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.persistence.model.Sample;
import com.perfectmatch.persistence.model.Style;
import com.perfectmatch.web.services.impl.SampleServiceBean;

import reactor.core.publisher.Flux;

@RunWith(MockitoJUnitRunner.class)
public class SampleServiceTest {
	
	@Mock
    private SampleRepository sampleRepository;

	private SampleServiceBean sampleService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		this.sampleService = new SampleServiceBean();
		sampleService.setDao(sampleRepository);
	}

	@Test
	public void givenSampleInTheRepo_whenFindByName_shouldReturnIt() {
		//Given
		
        Music musicDef = Music.ofType(Style.TECH_HOUSE)
        		.withArtist("Latmun")
        		.withName("def (Original Mix)")
        		.build();

		Sample sampleDef = new Sample();
        String sampleName = musicDef.getArtist() + ":" + musicDef.getName();
		sampleDef.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        sampleDef.setName(sampleName);
        
        when(this.sampleRepository.findByName(sampleName)).thenReturn(Flux.just(sampleDef));
        
	    //when    
		Sample actualSample =  this.sampleService.findByName(sampleName).blockFirst();
		
		//Then
		verify(this.sampleRepository).findByName(sampleName);
		assertEquals(sampleDef, actualSample);
		
		
	}
	
	
}
