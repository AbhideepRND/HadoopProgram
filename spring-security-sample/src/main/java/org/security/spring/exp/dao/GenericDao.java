package org.security.spring.exp.dao;

import java.util.List;

import org.security.spring.exp.enums.NativeSqlQueryName;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public interface GenericDao<BO> {

	public List<BO> search(NativeSqlQueryName queryNames, SqlParameterSource sqlparameter, RowMapper<BO> rowmapper);
	
	
}
