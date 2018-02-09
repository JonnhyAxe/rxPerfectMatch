package com.perfectmatch.common.interfaces;

import com.perfectmatch.persistence.model.Match;
import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.persistence.model.Sample;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This interface is used by the business logic as the name 'Searchable' (rather
 * than Queryable) name implies
 *
 */
public interface ByNameSearchable<T extends ByNameQueryable> {

	Mono<T> findByName(final String name);
}
