package com.perfectmatch.web.services.impl;

import java.util.Set;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfectmatch.common.persistence.services.AbstractRawService;
import com.perfectmatch.persistence.dao.MusicRepository;
import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.web.services.MusicService;

import reactor.core.publisher.Flux;

/**
 * Expose web services for Music Entity
 *
 */
@Service
//@Transactional
public class MusicServiceBean extends AbstractRawService<Music>  implements MusicService { 

    @Autowired
    private MusicRepository dao;

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

}
