package com.perfectmatch.web.services;

import com.perfectmatch.common.interfaces.ByNameSearchable;
import com.perfectmatch.common.interfaces.IOperations;
import com.perfectmatch.common.services.PersistenceService;
import com.perfectmatch.persistence.model.Match;

/**
 * This class aims to expose Match (between Samples) related services for Web
 * Controllers
 *
 */
public interface SampleMatchService extends PersistenceService<Match>, IOperations<Match>, ByNameSearchable<Match> {

    //

}
