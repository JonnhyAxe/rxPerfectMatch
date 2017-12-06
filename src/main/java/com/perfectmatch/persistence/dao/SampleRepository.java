package com.perfectmatch.persistence.dao;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.persistence.model.Sample;

public interface SampleRepository extends ReactiveCrudRepository<Sample, ObjectId> {

}
