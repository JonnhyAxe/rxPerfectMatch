package com.perfectmatch.persistence.dao;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.perfectmatch.persistence.model.Music;

public interface MusicRepository extends ReactiveCrudRepository<Music, ObjectId> {

}
