package com.perfectmatch.web.services.impl;

import java.util.Optional;
import java.util.Set;

import org.bson.types.ObjectId;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfectmatch.common.persistence.services.AbstractRawService;
import com.perfectmatch.persistence.dao.MatchRepository;
import com.perfectmatch.persistence.dao.MusicRepository;
import com.perfectmatch.persistence.dao.SampleRepository;
import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.persistence.model.Sample;
import com.perfectmatch.web.services.MusicService;

import reactor.core.publisher.Flux;

/**
 * Expose web services for Music Entity
 *
 */
@Service
public class MusicServiceBean extends AbstractRawService<Music>  implements MusicService { 

    @Autowired
    private MusicRepository dao;
    
    @Autowired
    private SampleRepository sampleDao;
    
    @Autowired
    private MatchRepository matchDao;



	/**
     * @return the MusicRepository
     */
    @Override
    public MusicRepository getDao() {

        return dao;
    }

    @Override
    public Flux<Music> findByName(final String name) {

        return getDao().findByName(name);
    }

    @Override
    public Set<Music> findByArtist(final String name) {
    	//TODO: change to return a Flux
        return getDao().findByArtist(name);
    }

	@Override
	public void update(final Music music) {
		//supported only for samples
		 Music musicToUpdate = this.getDao().findById(music.getId()).block();
		 
		 Set<Sample> samplesSaved = musicToUpdate.getSamples();
		 Set<Sample> samplesToUpdate = music.getSamples();
		 if(!samplesSaved.equals(samplesToUpdate)) {
			//for each sample verify if exist in the database
			 samplesToUpdate.stream()
			 .filter(sample -> !this.getSampleDao().findById(sample.getId()).hasElement().block())
			 .forEach(sample -> this.getSampleDao().save(sample));
		 }
		 
	}
	
	 @Override
	 public void delete(final ObjectId id) throws Exception {
	
		 //TODO: extract code to an SampleUtils module
		 Music musictoDelete = this.getDao().findById(id).block(); //TODO: is this reactive?
		 Optional<Sample> musichSampleAsMatch =  musictoDelete.getSamples().stream()
		 	.filter(sample -> this.getMatchDao().findById(sample.getId()).hasElement().block())
		 	.findFirst();
		 
		 if(musichSampleAsMatch.isPresent()) {
			 //TODO: change for a custom exception
			 throw new Exception("Music cannot be removed because it has sample match");
		 }
		 
		 super.delete(id);
	 }
	 
	public void setDao(MusicRepository dao) {
		this.dao = dao;
		
	}

	private SampleRepository getSampleDao() {
		return sampleDao;
	}

	public void setSampleDao(SampleRepository sampleDao) {
		this.sampleDao = sampleDao;
	}


    public MatchRepository getMatchDao() {
		return matchDao;
	}

	public void setMatchDao(MatchRepository matchDao) {
		this.matchDao = matchDao;
	}
	
}
