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
import com.perfectmatch.persistence.model.Match;
import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.persistence.model.Sample;
import com.perfectmatch.persistence.model.Style;

@RunWith(SpringJUnit4ClassRunner.class)
@Import(PerfectMatchApplication.class)
//@TestPropertySource(locations = "classpath:application.properties")
public class MatchRepositoryTest {

	@Autowired
	private MatchRepository repo;

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
	public void shouldCreateAMatch() {

		// Given
        Music musicPleaseStop = new Music();
        musicPleaseStop.setArtist("Latmun");
        musicPleaseStop.setName("Please Stop (Original Mix)");
        musicPleaseStop.setStyle(Style.TECH_HOUSE.name());

        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(musicPleaseStop.getArtist() + ":" + musicPleaseStop.getName());

        musicPleaseStop.setSamples(new HashSet<Sample>(Arrays.asList(samplePleaseStop)));

        Music musicDef = new Music();
        musicDef.setArtist("Latmun");
        musicDef.setName("def (Original Mix)");
        musicDef.setStyle(Style.TECH_HOUSE.name());

        Sample sampleDef = new Sample();
        sampleDef.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        sampleDef.setName(musicDef.getArtist() + ":" + musicDef.getName());

        musicDef.setSamples(new HashSet<Sample>(Arrays.asList(sampleDef)));


        Match newMatch = new Match();
        newMatch.setName(samplePleaseStop.getName());
        newMatch.setSampleFromRule(sampleDef.getName());
        newMatch.setRule("BY_SAME_ARTIST_NAME");


		// When
		final Match saved = repo.save(newMatch).block();

		// Then
		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getRule()).isEqualTo("BY_SAME_ARTIST_NAME");
		
	}

	@Test
	public void shouldFindAMatchWithItsId() {

		// Given
        Music musicPleaseStop = new Music();
        musicPleaseStop.setArtist("Latmun");
        musicPleaseStop.setName("Please Stop (Original Mix)");
        musicPleaseStop.setStyle(Style.TECH_HOUSE.name());

        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(musicPleaseStop.getArtist() + ":" + musicPleaseStop.getName());

        musicPleaseStop.setSamples(new HashSet<Sample>(Arrays.asList(samplePleaseStop)));

        Music musicDef = new Music();
        musicDef.setArtist("Latmun");
        musicDef.setName("def (Original Mix)");
        musicDef.setStyle(Style.TECH_HOUSE.name());

        Sample sampleDef = new Sample();
        sampleDef.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        sampleDef.setName(musicDef.getArtist() + ":" + musicDef.getName());

        musicDef.setSamples(new HashSet<Sample>(Arrays.asList(sampleDef)));


        Match newMatch = new Match();
        newMatch.setName(samplePleaseStop.getName());
        newMatch.setSampleFromRule(sampleDef.getName());
        newMatch.setRule("BY_SAME_ARTIST_NAME");

		final Match saved = repo.save(newMatch).block();
		
		// When
		final Match retrieved = repo.findById(saved.getId()).block();

		// Then
		assertThat(retrieved).isNotNull();
		assertThat(retrieved.getId()).isEqualTo(saved.getId());
		assertThat(retrieved.getName()).isEqualTo(saved.getName());
	}

	@Test
	public void shouldUpdateSampleName() {

		// Given
        Music musicPleaseStop = new Music();
        musicPleaseStop.setArtist("Latmun");
        musicPleaseStop.setName("Please Stop (Original Mix)");
        musicPleaseStop.setStyle(Style.TECH_HOUSE.name());

        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(musicPleaseStop.getArtist() + ":" + musicPleaseStop.getName());

        musicPleaseStop.setSamples(new HashSet<Sample>(Arrays.asList(samplePleaseStop)));

        Music musicDef = new Music();
        musicDef.setArtist("Latmun");
        musicDef.setName("def (Original Mix)");
        musicDef.setStyle(Style.TECH_HOUSE.name());

        Sample sampleDef = new Sample();
        sampleDef.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        sampleDef.setName(musicDef.getArtist() + ":" + musicDef.getName());

        musicDef.setSamples(new HashSet<Sample>(Arrays.asList(sampleDef)));


        Match newMatch = new Match();
        newMatch.setName(samplePleaseStop.getName());
        newMatch.setSampleFromRule(sampleDef.getName());
        newMatch.setRule("BY_SAME_ARTIST_NAME");

		final Match saved = repo.save(newMatch).block();
		

		// When
		final Match retrieved = repo.findById(saved.getId()).block();
		retrieved.setRule("BY_SAME_KEY");
		final Match updated = repo.save(retrieved).block();

		// Then
		assertThat(updated.getId()).isEqualTo(retrieved.getId());
		assertThat(updated.getRule()).isEqualTo("BY_SAME_KEY");
	}

	@Test
	public void shouldDeleteMatch() {

		// Given
        Music musicPleaseStop = new Music();
        musicPleaseStop.setArtist("Latmun");
        musicPleaseStop.setName("Please Stop (Original Mix)");
        musicPleaseStop.setStyle(Style.TECH_HOUSE.name());

        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(musicPleaseStop.getArtist() + ":" + musicPleaseStop.getName());

        musicPleaseStop.setSamples(new HashSet<Sample>(Arrays.asList(samplePleaseStop)));

        Music musicDef = new Music();
        musicDef.setArtist("Latmun");
        musicDef.setName("def (Original Mix)");
        musicDef.setStyle(Style.TECH_HOUSE.name());

        Sample sampleDef = new Sample();
        sampleDef.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        sampleDef.setName(musicDef.getArtist() + ":" + musicDef.getName());

        musicDef.setSamples(new HashSet<Sample>(Arrays.asList(sampleDef)));


        Match newMatch = new Match();
        newMatch.setName(samplePleaseStop.getName());
        newMatch.setSampleFromRule(sampleDef.getName());
        newMatch.setRule("BY_SAME_ARTIST_NAME");

		final Match saved = repo.save(newMatch).block();
		

		// When
		repo.delete(saved).block();
		boolean exists = repo.existsById(saved.getId()).block();

		// Then
		assertThat(exists).isFalse();
	}

	@Test
	public void shouldReturnAllMatchs() {

		// Given
        Music musicPleaseStop = new Music();
        musicPleaseStop.setArtist("Latmun");
        musicPleaseStop.setName("Please Stop (Original Mix)");
        musicPleaseStop.setStyle(Style.TECH_HOUSE.name());

        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(musicPleaseStop.getArtist() + ":" + musicPleaseStop.getName());

        musicPleaseStop.setSamples(new HashSet<Sample>(Arrays.asList(samplePleaseStop)));

        Music musicDef = new Music();
        musicDef.setArtist("Latmun");
        musicDef.setName("def (Original Mix)");
        musicDef.setStyle(Style.TECH_HOUSE.name());

        Sample sampleDef = new Sample();
        sampleDef.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        sampleDef.setName(musicDef.getArtist() + ":" + musicDef.getName());

        musicDef.setSamples(new HashSet<Sample>(Arrays.asList(sampleDef)));


        Match newMatch = new Match();
        newMatch.setName(samplePleaseStop.getName());
        newMatch.setSampleFromRule(sampleDef.getName());
        newMatch.setRule("BY_SAME_ARTIST_NAME");

		repo.save(newMatch).block();
		

		// When
		final Iterable<Match> samples = repo.findAll().toIterable();

		// Then
		assertThat(samples).isNotNull();
		assertThat(samples.iterator()).hasSize(1);
	}

	@Test
	public void givenACleanDB_shouldReturnNoMatch() {

		// Given
		
		// When
		final Iterable<Match> samples = repo.findAll().toIterable();

		// Then
		assertThat(samples).isNotNull();
		assertThat(samples.iterator()).hasSize(0);
	}
}
