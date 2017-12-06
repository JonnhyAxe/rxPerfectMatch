package com.perfectmatch.persistence.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.perfectmatch.PerfectMatchApplication;
import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.persistence.model.Sample;
import com.perfectmatch.persistence.model.Style;

@RunWith(SpringJUnit4ClassRunner.class)
@Import(PerfectMatchApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class MusicRepositoryTest {

	@Autowired
	private MusicRepository repo;

	/**
	 * Class level @DirtiesContext(classMode=ClassMode.BEFORE_EACH_TEST_METHOD)
	 * annotation can be used instead of this method to reset the context for
	 * each test but execution will be much slower.
	 */
	@Before
	public void cleanDB() {
		repo.deleteAll().block();
	}

	@Test
	public void shouldCreateAMusic() {

		// Given
        String artist = "Latmun";
	    String name = "Please Stop (Original Mix)";
		
        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(artist + ":" + name);

		Music musicPleaseStop = Music.ofType(Style.TECH_HOUSE)
        		.withArtist(artist)
        		.withName(name)
        		.withSamples(new HashSet<Sample>(Arrays.asList(samplePleaseStop)))
        		.build();


		// When
		final Music saved = repo.save(musicPleaseStop).block();

		// Then
		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getArtist()).isEqualTo(artist);
		assertThat(saved.getName()).isEqualTo(name);
	}

	@Test
	public void shouldFindAPersonWithItsId() {

		// Given
        String artist = "Latmun";
	    String name = "Please Stop (Original Mix)";
		
        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(artist + ":" + name);

		Music musicPleaseStop = Music.ofType(Style.TECH_HOUSE)
        		.withArtist(artist)
        		.withName(name)
        		.withSamples(new HashSet<Sample>(Arrays.asList(samplePleaseStop)))
        		.build();
		final Music saved = repo.save(musicPleaseStop).block();

		// When
		final Music retrieved = repo.findById(saved.getId()).block();

		// Then
		assertThat(retrieved).isNotNull();
		assertThat(retrieved.getId()).isEqualTo(saved.getId());
		assertThat(retrieved.getName()).isEqualTo(saved.getName());
		assertThat(retrieved.getArtist()).isEqualTo(saved.getArtist());
	}

	@Test
	public void shouldUpdateMusicArtistName() {

		// Given
        String artist = "Latmun";
        String newArtistName = "Latmun";
	    String name = "Please Stop (Original Mix)";
		
        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(artist + ":" + name);

		Music musicPleaseStop = Music.ofType(Style.TECH_HOUSE)
        		.withArtist(artist)
        		.withName(name)
        		.withSamples(new HashSet<Sample>(Arrays.asList(samplePleaseStop)))
        		.build();
		final Music saved = repo.save(musicPleaseStop).block();
		final Music retrieved = repo.findById(saved.getId()).block();

		// When
		retrieved.setName(newArtistName);
		final Music updated = repo.save(retrieved).block();

		// Then
		assertThat(updated.getId()).isEqualTo(retrieved.getId());
		assertThat(updated.getName()).isEqualTo(newArtistName);
	}

	@Test
	public void shouldDeleteMusic() {

		// Given
        String artist = "Latmun";
	    String name = "Please Stop (Original Mix)";
		
        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(artist + ":" + name);

		Music musicPleaseStop = Music.ofType(Style.TECH_HOUSE)
        		.withArtist(artist)
        		.withName(name)
        		.withSamples(new HashSet<Sample>(Arrays.asList(samplePleaseStop)))
        		.build();
		final Music saved = repo.save(musicPleaseStop).block();

		// When
		repo.delete(saved).block();
		boolean exists = repo.existsById(saved.getId()).block();

		// Then
		assertThat(exists).isFalse();
	}

	@Test
	public void shouldReturnAllCustomers() {

		// Given
        String artist = "Latmun";
	    String pleaseStop = "Please Stop (Original Mix)";
	    String def = "def (Original Mix)";
	    
        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(artist + ":" + pleaseStop);

		Music musicPleaseStop = Music.ofType(Style.TECH_HOUSE)
        		.withArtist(artist)
        		.withName(pleaseStop)
        		.withSamples(new HashSet<Sample>(Arrays.asList(samplePleaseStop)))
        		.build();
		repo.save(musicPleaseStop).block();

        Sample sampleDef = new Sample();
        sampleDef.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        sampleDef.setName(artist + ":" + def);

		
        Music musicDef = new Music();
        musicDef.setArtist(artist);
		musicDef.setName(def);
        musicDef.setStyle(Style.TECH_HOUSE.name());
        
    	Music musicdef = Music.ofType(Style.TECH_HOUSE)
        		.withArtist(artist)
        		.withName(pleaseStop)
        		.withSamples(new HashSet<Sample>(Arrays.asList(sampleDef)))
        		.build();
    	repo.save(musicdef).block();

		// When
		final Iterable<Music> customers = repo.findAll().toIterable();

		// Then
		assertThat(customers).isNotNull();
		assertThat(customers.iterator()).hasSize(2);
	}

	@Test
	public void givenACleanDB_shouldReturnNoCustomers() {

		// Given
		
		// When
		final Iterable<Music> customers = repo.findAll().toIterable();

		// Then
		assertThat(customers).isNotNull();
		assertThat(customers.iterator()).hasSize(0);
	}
}
