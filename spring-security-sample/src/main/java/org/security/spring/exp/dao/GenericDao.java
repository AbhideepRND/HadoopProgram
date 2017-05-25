package org.security.spring.exp.dao;

import java.util.List;

import org.security.spring.exp.enums.NativeSqlQueryName;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public abstract class GenericDao<BO> {

	abstract protected List<BO> search(NativeSqlQueryName queryNames, SqlParameterSource sqlparameter, RowMapper<BO> rowmapper);
	
	
}