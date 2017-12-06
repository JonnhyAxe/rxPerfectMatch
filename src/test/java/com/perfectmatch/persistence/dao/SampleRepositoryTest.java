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
//@TestPropertySource(locations = "classpath:application.properties")
public class SampleRepositoryTest {

	@Autowired
	private SampleRepository repo;

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
	public void shouldCreateASample() {

		// Given
        String artist = "Latmun";
	    String name = "Please Stop (Original Mix)";
		String sampleName = artist + ":" + name;
        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(artist + ":" + name);


		// When
		final Sample saved = repo.save(samplePleaseStop).block();

		// Then
		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getName()).isEqualTo(sampleName);
	}

	@Test
	public void shouldFindASampleWithItsId() {

		// Given
        String artist = "Latmun";
	    String name = "Please Stop (Original Mix)";
        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(artist + ":" + name);

		final Sample saved = repo.save(samplePleaseStop).block();

		// When
		final Sample retrieved = repo.findById(saved.getId()).block();

		// Then
		assertThat(retrieved).isNotNull();
		assertThat(retrieved.getId()).isEqualTo(saved.getId());
		assertThat(retrieved.getName()).isEqualTo(saved.getName());
	}

	@Test
	public void shouldUpdateSampleName() {

		// Given
        String artist = "Latmun";
	    String name = "Please Stop (Original Mix)";
        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(artist + ":" + name);

		final Sample saved = repo.save(samplePleaseStop).block();

		// When
		final Sample retrieved = repo.findById(saved.getId()).block();
		retrieved.setName(artist + name);

		// When
		final Sample updated = repo.save(retrieved).block();

		// Then
		assertThat(updated.getId()).isEqualTo(retrieved.getId());
		assertThat(updated.getName()).isEqualTo(artist + name);
	}

	@Test
	public void shouldDeleteSample() {

		// Given
        String artist = "Latmun";
	    String name = "Please Stop (Original Mix)";
        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(artist + ":" + name);

		final Sample saved = repo.save(samplePleaseStop).block();

		// When
		repo.delete(saved).block();
		boolean exists = repo.existsById(saved.getId()).block();

		// Then
		assertThat(exists).isFalse();
	}

	@Test
	public void shouldReturnAllSamples() {

		// Given
        String artist = "Latmun";
	    String nameMusic = "Please Stop (Original Mix)";
	    
        Sample samplePleaseStop = new Sample();
        samplePleaseStop.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        samplePleaseStop.setName(artist + ":" + nameMusic);


		repo.save(samplePleaseStop).block();

	    String defMusic = "def";
	    
        Sample sampleDef = new Sample();
        sampleDef.setTimestamp(3 * 60); // Start time stamp at 00:03:00m
        sampleDef.setName(artist + ":" + defMusic);

    	repo.save(sampleDef).block();

		// When
		final Iterable<Sample> samples = repo.findAll().toIterable();

		// Then
		assertThat(samples).isNotNull();
		assertThat(samples.iterator()).hasSize(2);
	}

	@Test
	public void givenACleanDB_shouldReturnNoSamples() {

		// Given
		
		// When
		final Iterable<Sample> samples = repo.findAll().toIterable();

		// Then
		assertThat(samples).isNotNull();
		assertThat(samples.iterator()).hasSize(0);
	}
}
