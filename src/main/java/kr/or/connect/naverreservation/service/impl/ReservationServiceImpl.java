package kr.or.connect.naverreservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.naverreservation.dao.ReservationDao;
import kr.or.connect.naverreservation.dto.Comment;
import kr.or.connect.naverreservation.dto.Product;
import kr.or.connect.naverreservation.dto.ReservationInfo;
import kr.or.connect.naverreservation.dto.ReservationInfoResponse;
import kr.or.connect.naverreservation.dto.ReservationParam;
import kr.or.connect.naverreservation.dto.ReservationResponse;
import kr.or.connect.naverreservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDao reservationDao;
	
	@Override
	public ReservationInfoResponse getReservationInfoResponse(String email) {
		return reservationDao.selectReservationInfoResponse(email);
	}

	@Override
	public Product getProductInfo(Integer reservationInfoId) {
		// TODO Auto-generated method stub
		return reservationDao.selectProductByReservationInfoId(reservationInfoId);
	}

	@Override
	@Transactional
	public boolean setReservation(ReservationParam reservationParam) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean removeReservation(Integer reservationId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean setComment(Integer reservationId, Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
