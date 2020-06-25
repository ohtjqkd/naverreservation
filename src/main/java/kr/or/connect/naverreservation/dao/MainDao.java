package kr.or.connect.naverreservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.naverreservation.dto.Category;
import kr.or.connect.naverreservation.dto.MainContentDto;

import static kr.or.connect.naverreservation.dao.MainDaoSqls.*;

@Repository
public class MainDao {
	NamedParameterJdbcTemplate jdbc;
	RowMapper<MainContentDto> rowMapper = new BeanPropertyRowMapper<MainContentDto>(MainContentDto.class);
	RowMapper<Category> rowMapper1 = new BeanPropertyRowMapper<Category>(Category.class);
	
	public MainDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	public int countByCategoryId(int categoryId) {
		return jdbc.queryForObject(SELECT_COUNT_CATEGORY, Collections.singletonMap("categoryId", categoryId), Integer.class);
	}
	public int countAll() {
		return jdbc.queryForObject(SELECT_COUNT_ALL, Collections.emptyMap(),Integer.class);
	}
	public List<MainContentDto> selectAllContents(Integer start, Integer limit){
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("start",start);
		params.put("limit",limit);
		return jdbc.query(SELECT_ALL_CONTENTS, params, rowMapper);
	}
	public List<MainContentDto> selectContentsByCategoryId(int categoryId, Integer start, Integer limit){
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("categoryId", categoryId);
		params.put("start",start);
		params.put("limit",limit);
		return jdbc.query(SELECT_CONTENT_BY_CATEGORY, params, rowMapper);
	}
	public List<Category> selectCategory(){
		return jdbc.query(SELECT_ALL_CATEGORY, rowMapper1);
	}
	public List<MainContentDto> selectPromotions(){
		return jdbc.query(SELECT_ALL_PROMOTIONS, rowMapper);
	}
}
