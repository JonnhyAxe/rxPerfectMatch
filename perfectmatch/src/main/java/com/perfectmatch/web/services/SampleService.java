package com.perfectmatch.web.services;

import com.perfectmatch.common.interfaces.ByNameSearchable;
import com.perfectmatch.common.interfaces.IOperations;
import com.perfectmatch.common.services.PersistenceService;
import com.perfectmatch.persistence.model.Sample;

/**
 * This class aims to expose Sample related services for Web Controllers
 *
 */
public interface SampleService extends PersistenceService<Sample>, IOperations<Sample>, ByNameSearchable<Sample> {

}
