package kr.or.connect.naverreservation.dto;

import java.util.List;

public class ReservationInfoResponse {
	private List<ReservationInfo> reservations;
	private Integer size;
	public List<ReservationInfo> getReservations() {
		return reservations;
	}
	public void setReservations(List<ReservationInfo> reservations) {
		this.reservations = reservations;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "ReservationInfoResponse [reseravtions=" + reservations + ", size=" + size + "]";
	}
	
}
