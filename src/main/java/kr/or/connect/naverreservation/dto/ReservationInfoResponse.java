package kr.or.connect.naverreservation.dto;

import java.util.List;

public class ReservationInfoResponse {
	private List<ReservationInfo> reseravtions;
	private Integer size;
	public List<ReservationInfo> getReseravtions() {
		return reseravtions;
	}
	public void setReseravtions(List<ReservationInfo> reseravtions) {
		this.reseravtions = reseravtions;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "ReservationInfoResponse [reseravtions=" + reseravtions + ", size=" + size + "]";
	}
	
}
