package com.perfectmatch.web.services.impl;

import org.assertj.core.util.Preconditions;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfectmatch.common.persistence.services.AbstractRawService;
import com.perfectmatch.persistence.dao.MatchRepository;
import com.perfectmatch.persistence.model.Match;
import com.perfectmatch.web.services.MatchService;

import reactor.core.publisher.Flux;

/**
 *
 * Expose web services for Match Entity
 *
 */
@Service
//@Transactional
public class SampleMatchServiceBean extends AbstractRawService<Match> implements MatchService {

    @Autowired
    private MatchRepository dao;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.perfectmatch.common.persistence.srvices.AbstractRawService#getDao()
     */
    @Override
    protected MatchRepository getDao() {

        return this.dao;
    }
    
    //only for test purposes
    public void setDao(MatchRepository dao) {

       this.dao = dao;
    
    }
    
    /*
     * (non-Javadoc)
     *
     * @see
     * com.perfectmatch.common.interfaces.ByNameSearchable#findByName(java.lang.
     * String)
     */
    @Override
    public Flux<Match> findByName(String name) {

       throw new UnsupportedOperationException();
    }

	@Override
	public void update(final Match entity) {

		 throw new UnsupportedOperationException();
	}
}
