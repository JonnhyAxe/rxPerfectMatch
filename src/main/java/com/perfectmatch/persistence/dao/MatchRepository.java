package com.perfectmatch.persistence.dao;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.perfectmatch.persistence.model.Match;

public interface MatchRepository extends ReactiveCrudRepository<Match, ObjectId> {

}
