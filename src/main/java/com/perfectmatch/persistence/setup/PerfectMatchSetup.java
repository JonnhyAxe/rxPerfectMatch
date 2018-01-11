package com.perfectmatch.persistence.setup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.perfectmatch.persistence.model.Match;
import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.persistence.model.Sample;
import com.perfectmatch.persistence.model.Style;
import com.perfectmatch.web.services.MusicService;
import com.perfectmatch.web.services.MatchService;
import com.perfectmatch.web.services.SampleService;

/**
 * This simple setup class will run during the bootstrap process of Spring and
 * will create some setup data <br>
 * The main focus here is creating some Music artist, Samples and Samples'
 * matches
 */
@Component
public class PerfectMatchSetup implements ApplicationListener<ContextRefreshedEvent> {

    // Only for setup purposes
    private boolean setupDone;

    @Autowired
    private MusicService musicService;

    @Autowired
    
    private SampleService sampleService;

    @Autowired
    private MatchService matchService;

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.context.ApplicationListener#onApplicationEvent(org.
     * springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        if (!setupDone) {
        	musicService.deleteAll();
        	sampleService.deleteAll();
        	matchService.deleteAll();
            createMusic();
        }

    }


    /**
     * Create Music data in the DB
     */
    private void createMusic() {

    	
        Music musicPleaseStop = new Music();
        musicPleaseStop.setArtist("Latmun");
        musicPleaseStop.setName("Please Stop (Original Mix)");
        musicPleaseStop.setStyle(Style.TECH_HOUSE.name());

        Music musicPleaseStopSaved = musicService.create(musicPleaseStop).block();//TODO: is this pure reactive?
        
        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(musicPleaseStop.getArtist() + ":" + musicPleaseStop.getName());

        Sample savedSamplePleaseStop = this.sampleService.create(samplePleaseStop).block();
        musicPleaseStop.setSamples(new HashSet<Sample>(Arrays.asList(samplePleaseStop)));

        Music musicDef = new Music();
        musicDef.setArtist("Latmun");
        musicDef.setName("def (Original Mix)");
        musicDef.setStyle(Style.TECH_HOUSE.name());
        
        Music musicDefSaved = musicService.create(musicDef).block();
        

        Sample sampleDef = new Sample();
        sampleDef.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        sampleDef.setName(musicDef.getArtist() + ":" + musicDef.getName());
        Sample savedSampleDef = this.sampleService.create(samplePleaseStop).block();
        musicDef.setSamples(new HashSet<Sample>(Arrays.asList(sampleDef)));


        Match newMatch = new Match();
        newMatch.setThisSample(savedSamplePleaseStop.getId());
        newMatch.setThatSampleFromRule(savedSampleDef.getId());
        newMatch.setRule("BY_SAME_ARTIST_NAME");

        matchService.create(newMatch).block();
        //TODO: find by name do not work
//        if (Objects.isNull(sampleMatchService.findByName(newMatch.getName()))) {
//            sampleMatchService.create(newMatch);
//        }
//
//        if (Objects.isNull(sampleService.findByName(samplePleaseStop.getName()))) {
//            sampleService.create(samplePleaseStop);
//        }
//
//        if (Objects.isNull(sampleService.findByName(sampleDef.getName()))) {
//            sampleService.create(sampleDef);
//        }
//
//        if (Objects.isNull(musicService.findByName(musicPleaseStop.getName()))) {
//            musicService.create(musicPleaseStop);
//        }
//
//        if (Objects.isNull(musicService.findByName(musicDef.getName()))) {
//            musicService.create(musicDef);
//        }

        // Test insert music without samples and Match

        Music music = new Music();
        music.setArtist("LatmunXPTO");
        music.setName("Please Stop (Original Mix)XPTO");
        music.setStyle(Style.TECH_HOUSE.name());

        Music musicSaved = musicService.create(music).block();

    }

}