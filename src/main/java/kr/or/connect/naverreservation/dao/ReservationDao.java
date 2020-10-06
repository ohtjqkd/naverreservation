package kr.or.connect.naverreservation.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.naverreservation.dto.Product;
import kr.or.connect.naverreservation.dto.ReservationInfo;
import kr.or.connect.naverreservation.dto.ReservationInfoResponse;

import static kr.or.connect.naverreservation.dao.ReservationDaoSqls.*;

@Repository
public class ReservationDao {
	NamedParameterJdbcTemplate jdbc;
	@Autowired
	ProductDao productDao;
	
	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ReservationInfoResponse selectReservationInfoResponse(String email) {
		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();
		List<ReservationInfo> reservationInfos = selectReservations(email);
		reservationInfoResponse.setReservations(reservationInfos);
		reservationInfoResponse.setSize(reservationInfos.size());
		return reservationInfoResponse;
	}
	
	public List<ReservationInfo> selectReservations(String email){
		RowMapper<ReservationInfo> reservationInfoMapper = new BeanPropertyRowMapper<ReservationInfo>(ReservationInfo.class);
		try {
			List<ReservationInfo> reservationInfo = jdbc.query(SELECT_RESERVATIONS_BY_EMAIL, Collections.singletonMap("reservationEmail", email), reservationInfoMapper);
			for(ReservationInfo r:reservationInfo) {
				System.out.println(r);
				r.setDisplayInfo(productDao.selectDisplayInfo(r.getDisplayInfoId()));
			};
			return reservationInfo;
		} catch (Exception e) {
			List<ReservationInfo> listReservationInfo = new ArrayList<ReservationInfo>();
			return listReservationInfo;
		}
	}
	
	public Product selectProductByReservationInfoId(Integer reservationInfoId) {
		RowMapper<Product> productMapper = new BeanPropertyRowMapper<Product>(Product.class);
		return jdbc.queryForObject(SELECT_RESERVATION_INFO_BY_RESERVATION_INFO_ID, Collections.singletonMap("reservationInfoId", reservationInfoId), productMapper);
	}
	
}
