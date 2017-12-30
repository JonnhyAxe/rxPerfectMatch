package com.perfectmatch.web.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.perfectmatch.persistence.dao.MatchRepository;
import com.perfectmatch.persistence.model.Match;
import com.perfectmatch.web.services.impl.SampleMatchServiceBean;

import reactor.core.publisher.Mono;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTest {

    @Mock
    private MatchRepository dao;
    
	private SampleMatchServiceBean matchService;
	
	
	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	
	    matchService = new SampleMatchServiceBean();
	    matchService.setDao(this.dao);
	}
	
	@Test
	public void createSampleMatch() {
		//Given
      
		ObjectId fromSample = ObjectId.get();
		ObjectId thatSampleMatched = ObjectId.get();
		
	    Match newMatch = new Match();
        newMatch.setThisSample(fromSample);
        newMatch.setThatSampleFromRule(thatSampleMatched);
        newMatch.setRule("BY_SAME_ARTIST_NAME");
        
        Mono<Match> savedMatch = Mono.just(newMatch);
        when(matchService.create(newMatch)).thenReturn(savedMatch);

        
        //When
        Match newMatchCreated = matchService.create(newMatch).block();
        
        //Then
        assertEquals(fromSample, newMatchCreated.getThisSample());
        assertEquals(thatSampleMatched, newMatchCreated.getThatSampleFromRule());
        verify(dao, times(1)).save(newMatch);
        
	}
	
	@Test
	public void deleteSampleMatch() throws Exception {
		//Given
        matchService.setDao(this.dao);
		ObjectId fromSample = ObjectId.get();
		ObjectId thatSampleMatched = ObjectId.get();
		
	    Match newMatch = new Match();
	    newMatch.setId(ObjectId.get());
        newMatch.setThisSample(fromSample);
        newMatch.setThatSampleFromRule(thatSampleMatched);
        newMatch.setRule("BY_SAME_ARTIST_NAME");
        
        Mono<Match> savedMatch = Mono.just(newMatch);

        when(dao.findById(newMatch.getId())).thenReturn(savedMatch);

        
        //When
        matchService.delete(newMatch.getId());
        
        //Then
        verify(dao, times(1)).delete(newMatch);
        
	}
	

	@Test(expected = UnsupportedOperationException.class)
	public void findSampleMatchByName() {
		//Given
		String sampleName = "sampleName";
		
		//when
		matchService.findByName(sampleName);
		
		//then 
	}	//expected = UnsupportedOperationException.class
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void stackSampleMatchByName() {
		//Given
        matchService.setDao(this.dao);
		ObjectId fromSample = ObjectId.get();
		ObjectId thatSampleMatched = ObjectId.get();
		
	    Match newMatch = new Match();
        newMatch.setThisSample(fromSample);
        newMatch.setThatSampleFromRule(thatSampleMatched);
        newMatch.setRule("BY_SAME_ARTIST_NAME");
		//when
		matchService.update(newMatch);
		
		//then 
	}	//expected = UnsupportedOperationException.class
	
}
