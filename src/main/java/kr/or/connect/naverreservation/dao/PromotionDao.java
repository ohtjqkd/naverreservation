package kr.or.connect.naverreservation.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.naverreservation.dto.Promotion;

@Repository
public class PromotionDao {
	NamedParameterJdbcTemplate jdbc;
	static final String SELECT_PROMOTION_ALL = "SELECT id, product_id FROM promotion";
	
	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource); 
	}
	
	public List<Promotion> selectPromotions(){
		RowMapper<Promotion> promotionMapper = new BeanPropertyRowMapper<Promotion>(Promotion.class);
		return jdbc.query(SELECT_PROMOTION_ALL, promotionMapper);
	}
}
