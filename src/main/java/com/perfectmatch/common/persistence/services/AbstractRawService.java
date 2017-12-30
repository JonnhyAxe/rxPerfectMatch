package com.perfectmatch.common.persistence.services;

import java.util.List;

import org.assertj.core.util.Preconditions;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.perfectmatch.common.interfaces.IOperations;
import com.perfectmatch.common.model.NameableEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Transactional
public abstract class AbstractRawService<T extends NameableEntity> implements IOperations<T> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractRawService() {
		super();
	}

	// API

	// search

	// find - one

	// TODO: whey this
	// @Override
	// @Transactional(readOnly = true)
	// public T findOne(final long id) {
	//
	// return getDao().findOne(id);
	// }

	// find - all

	@Override
	@Transactional(readOnly = true)
	public Mono<List<T>> findAll() {

		return getDao().findAll().collectList();
	}

	// save/create/persist

	@Override
	public Mono<T> create(final T entity) {

		Preconditions.checkNotNull(entity);
		return getDao().save(entity);
	}

	// update/merge

	@Override
	public void update(final T entity) {

		Preconditions.checkNotNull(entity);

		getDao().save(entity);
	}

	// delete

	@Override
	public void deleteAll() {

		getDao().deleteAll();
	}

	 @Override
	 public void delete(final ObjectId id) throws Exception {
	
		 final T entity = getDao().findById(id).block();
		 //ServicePreconditions.checkEntityExists(entity);
		
		 getDao().delete(entity);
	 }

	 //TODO : 	 public void delete(final T id) throws Exception {

	// count

	@Override
	public Mono<Long> count() {

		return getDao().count();
	}

	// template method

	protected abstract ReactiveCrudRepository<T, ObjectId> getDao();
	// protected abstract PagingAndSortingRepository<T, Long> getDao();
	// protected abstract JpaSpecificationExecutor<T> getSpecificationExecutor();

	// template

	protected final Sort constructSort(final String sortBy, final String sortOrder) {

		Sort sortInfo = null;
		if (sortBy != null) {
			sortInfo = new Sort(Direction.fromString(sortOrder), sortBy);
		}
		return sortInfo;
	}

}