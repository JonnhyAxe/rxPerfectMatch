package com.perfectmatch.web.services;

import com.perfectmatch.common.interfaces.ByArtistSearchable;
import com.perfectmatch.common.interfaces.ByNameSearchable;
import com.perfectmatch.common.interfaces.IOperations;
import com.perfectmatch.common.services.PersistenceService;
import com.perfectmatch.persistence.model.Music;

/**
 * This class aims to expose Music related services for Web Controllers
 *
 */
public interface MusicService extends PersistenceService<Music>, IOperations<Music>, ByNameSearchable<Music>, ByArtistSearchable<Music> {

}
