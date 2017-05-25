package org.security.spring.exp.dao.impl;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.security.spring.exp.bo.BoTemplate;
import org.security.spring.exp.dao.GenericDao;
import org.security.spring.exp.enums.NativeSqlQueryName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;


public abstract class GenericDaoImpl<BO extends BoTemplate> extends GenericDao<BO> {

	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier(value="sqlProperties")
	private Properties properties; 
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	protected List<BO> search(NativeSqlQueryName queryNames, SqlParameterSource sqlparameter, RowMapper<BO> rowmapper) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		final String serachQuery = (String)properties.get(queryNames.getPropertyName());
		final List<BO> query = this.namedParameterJdbcTemplate.query(serachQuery, sqlparameter, rowmapper);
		return query;
	}
	
	public abstract List<BO> search(BO data);

	
	
}