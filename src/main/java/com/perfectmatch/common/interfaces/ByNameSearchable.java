package com.perfectmatch.common.interfaces;

import reactor.core.publisher.Flux;

/**
 * This interface is used by the business logic as the name 'Searchable' (rather
 * than Queryable) name implies
 *
 */
public interface ByNameSearchable<T extends ByNameQueryable> {

	Flux<T> findByName(final String name);
}
