package oa.dao.impl;

import oa.dao.BugTypeDaoI;
import oa.model.Tbugtype;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


@Repository
public class BugTypeDaoImpl extends BaseDaoImpl<Tbugtype> implements BugTypeDaoI {

	@Override
	@Cacheable(value = "bugTypeDaoCache", key = "#id")
	public Tbugtype getById(String id) {
		return super.get(Tbugtype.class, id);
	}

}
