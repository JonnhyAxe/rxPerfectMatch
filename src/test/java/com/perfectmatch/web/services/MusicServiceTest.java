package com.perfectmatch.web.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.perfectmatch.common.interfaces.IOperations;
import com.perfectmatch.persistence.dao.MatchRepository;
import com.perfectmatch.persistence.dao.MusicRepository;
import com.perfectmatch.persistence.dao.SampleRepository;
import com.perfectmatch.persistence.model.Match;
import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.persistence.model.Sample;
import com.perfectmatch.persistence.model.Style;
import com.perfectmatch.web.services.impl.MusicServiceBean;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(MockitoJUnitRunner.class)
public class MusicServiceTest {
	
    @Mock
    private MusicRepository dao;
    
    @Mock
    private SampleRepository sampleDao;
    
    @Mock
    private MatchRepository matchDao;
    
    private MusicServiceBean musicService;
    
    @Before
    public void setUp() {
	    MockitoAnnotations.initMocks(this);

    	musicService = new MusicServiceBean();
    	musicService.setDao(this.dao);
    	musicService.setSampleDao(sampleDao);
    	musicService.setMatchDao(matchDao);
    	
    }
    
    @Test 
    public void findMusicByName() {
    	//Given
    	String musicName = "Please Stop (Original Mix)";
    	Music expectedMusic = Music.ofType(Style.HOUSE)
    			.withId(ObjectId.get())
    			.withArtist("Latmun")
    			.withName(musicName)
    			.build();
    	
        when(dao.findByName(musicName)).thenReturn(Flux.just(expectedMusic));

    	//When
    	Music savedMusic = this.musicService.findByName(musicName).blockFirst();
    	
    	//Then
    	verify(dao, times(1)).findByName(musicName);
    	assertEquals(expectedMusic, savedMusic);
    }
    
    @Test 
    public void findByArtist() {
    	//Given
    	Set<Music> musics = new HashSet<>();

    	String musicArtistName = "Latmun";
    	String musicName = "Please Stop (Original Mix)";
    	Music expectedMusic = Music.ofType(Style.HOUSE)
    			.withId(ObjectId.get())
    			.withArtist(musicArtistName)
    			.withName(musicName)
    			.build();
    	
    	musics.add(expectedMusic);

    	musicName = "def (Original Mix)";
    	expectedMusic = Music.ofType(Style.HOUSE)
     			.withId(ObjectId.get())
     			.withArtist(musicArtistName)
     			.withName(musicName)
     			.build();
    	 
    	musics.add(expectedMusic);
    	 
        when(dao.findByArtist(musicArtistName)).thenReturn(musics);

    	//When 
        Set<Music> savedMusics = this.musicService.findByArtist(musicArtistName);
    	
    	//Then
    	verify(dao, times(1)).findByArtist(musicArtistName);
    	assertEquals(musics, savedMusics);
    	
    }
    
    @Test
    public void updateMusicSamples() {
    	//Given
    	ObjectId id = ObjectId.get();
    	String musicArtistName = "Latmun";
    	String musicName = "Please Stop (Original Mix)";
    	
    	Sample sampleDef = new Sample();
    	
        Sample sampleDef2 = new Sample();
        ObjectId sampleId = ObjectId.get();
    	ObjectId sampleDef2ID = ObjectId.get();
    	
    	
    	Music expectedMusic = Music.ofType(Style.HOUSE)
    			.withId(id)
    			.withArtist(musicArtistName)
    			.withName(musicName)
    			.build();
    	

      
        sampleDef.setId(sampleId);
        sampleDef.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        sampleDef.setName(expectedMusic.getArtist() + ":" + expectedMusic.getName());
    	expectedMusic.setSamples(new HashSet<Sample>(Arrays.asList(sampleDef)));
    	
        when(dao.findById(id)).thenReturn(Mono.just(expectedMusic));
        when(sampleDao.findById(sampleId)).thenReturn(Mono.just(sampleDef));

       
    	Music actualMusic = Music.ofType(Style.HOUSE)
     			.withId(id)
     			.withArtist(musicArtistName)
     			.withName(musicName)
     			.build();
    	
    	

    	sampleDef2.setId(sampleDef2ID);
    	sampleDef2.setTimestamp(4 * 60); // Start time stamp at 00:04:00m
    	sampleDef2.setName(expectedMusic.getArtist() + ":" + expectedMusic.getName());
        actualMusic.setSamples(new HashSet<Sample>(Arrays.asList(sampleDef, sampleDef2)));
        
        when(sampleDao.findById(sampleDef2ID)).thenReturn(Mono.empty());
        when(sampleDao.save(sampleDef2)).thenReturn(Mono.just(sampleDef2));

    	
    	//When 
        this.musicService.update(actualMusic);
    	
    	//Then
    	verify(sampleDao, times(1)).save(sampleDef2);
    	verify(sampleDao, times(1)).findById(sampleId);
    	verify(sampleDao, times(1)).findById(sampleDef2ID);
    }
    
    
    @Test 
    public void givenMusicEmptyMatchs_shouldDeleteMusic() throws Exception {
    	//Given
    	Sample sampleDef = new Sample();
    	ObjectId musicId = ObjectId.get(); 
    	ObjectId sampleId = ObjectId.get();
    	String musicArtistName = "Latmun";
    	String musicName = "Please Stop (Original Mix)";
   	
    	
    	Music expectedMusic = Music.ofType(Style.HOUSE)
    			.withId(musicId)
    			.withArtist(musicArtistName)
    			.withName(musicName)
    			.build();
    	
        sampleDef.setId(sampleId);
        sampleDef.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        sampleDef.setName(expectedMusic.getArtist() + ":" + expectedMusic.getName());
      
    	expectedMusic.setSamples(new HashSet<Sample>(Arrays.asList(sampleDef)));
    
       
    	when(dao.findById(musicId)).thenReturn(Mono.just(expectedMusic));
        when(matchDao.findById(sampleId)).thenReturn(Mono.empty());

    	//When
        this.musicService.delete(expectedMusic.getId());
    	
    	//Then
    	verify(matchDao, times(1)).findById(sampleId);
        verify(dao, times(1)).delete(expectedMusic);
    }
    
    @Test (expected = Exception.class)
    public void givenMusicMatchs_shouldNotDeleteMusic() throws Exception {
    	//Given
    	Sample sampleDef = new Sample();
    	ObjectId sampleId = ObjectId.get();
    	String musicArtistName = "Latmun";
    	String musicName = "Please Stop (Original Mix)";
   	
    	
    	Music expectedMusic = Music.ofType(Style.HOUSE)
    			.withId(ObjectId.get())
    			.withArtist(musicArtistName)
    			.withName(musicName)
    			.build();
    	
        sampleDef.setId(sampleId);
        sampleDef.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        sampleDef.setName(expectedMusic.getArtist() + ":" + expectedMusic.getName());
      
    	expectedMusic.setSamples(new HashSet<Sample>(Arrays.asList(sampleDef)));
    	
    	Match match = new Match();
       
    	//when(dao.findById(musicId)).thenReturn(Mono.just(expectedMusic));
        when(matchDao.findById(sampleId)).thenReturn(Mono.just(match));

    	//When
        this.musicService.delete(expectedMusic.getId());
    	
    	//Then
    }
    
}
