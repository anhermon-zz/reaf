package reaf.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Component;

import reaf.db.beans.Category;

@Component
public class JdbcDaoImpl {
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	private static final String INSERT_CATEGORY  = "INSERT INTO category_mapping (site,external) values (?,?)";
	private static final String GET_CATEGORIES   = "SELECT * FROM category_mapping WHERE 1=1 AND site=? and external=?";
	
	private static final String INSERT_ATTRIBUTE = "INSERT INTO attribute_type (external,name) values (?,?)";
	private static final String GET_ATTRIBUTES   = "SELECT * FROM attribute_type WHERE 1=1 AND category=?";
	
	private static final String SELECT_VALUE_COUNT = "SELECT COUNT(*) as value_count FROM `values`";
	
	//Category
	public Category getCategory(short site, int external) throws SQLException{
		return jdbcTemplate.queryForObject(GET_CATEGORIES,new Object[]{site,external},new CategoryMapper()) ;
	}
	public void insertCategory(int id){
		jdbcTemplate.update(INSERT_CATEGORY, new Object[]{id});
	}
	private static final class CategoryMapper implements RowMapper<Category>{
		
		@Override
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category category = new Category();
			category.setId(rs.getLong("id"));
			category.setSite(rs.getShort("site"));
			category.setExternal(rs.getInt("external"));
			return category;
		}
		
	}
	//attribute
	public void insertAttribute(short site, int category,String name){
//		jdbcTemplate.(INSERT_CATEGORY, new Object[]{site,category});
	}
		
	public int getValueCount() throws SQLException{
		return jdbcTemplate.queryForObject(SELECT_VALUE_COUNT,Integer.class) ;
	}
	
	
	
	

}
