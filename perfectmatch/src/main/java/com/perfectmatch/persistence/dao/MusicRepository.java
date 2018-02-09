package com.perfectmatch.persistence.dao;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.perfectmatch.common.interfaces.ByArtistSearchable;
import com.perfectmatch.common.interfaces.ByNameSearchable;
import com.perfectmatch.persistence.model.Music;

public interface MusicRepository extends ReactiveCrudRepository<Music, ObjectId>,  ByNameSearchable<Music>, ByArtistSearchable<Music>  {

}
