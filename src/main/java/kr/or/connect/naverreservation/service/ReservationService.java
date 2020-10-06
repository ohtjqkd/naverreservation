package kr.or.connect.naverreservation.service;

import kr.or.connect.naverreservation.dto.Comment;
import kr.or.connect.naverreservation.dto.Product;
import kr.or.connect.naverreservation.dto.ReservationInfo;
import kr.or.connect.naverreservation.dto.ReservationInfoResponse;
import kr.or.connect.naverreservation.dto.ReservationParam;
import kr.or.connect.naverreservation.dto.ReservationResponse;

public interface ReservationService {
	public ReservationInfoResponse getReservationInfoResponse(String email);
	public Product getProductInfo(Integer reservationInfoId);
	public boolean setReservation(ReservationParam reservationParam);
	public boolean removeReservation(Integer reservationId);
	public boolean setComment(Integer reservationId, Comment comment);  
}
