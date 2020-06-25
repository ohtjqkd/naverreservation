package kr.or.connect.naverreservation.dto;

public class DetailInfoDto {
	private String description;
	private Object String;
	@Override
	public String toString() {
		return "DetailInfoDto [description=" + description + ", String=" + String + "]";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object getString() {
		return String;
	}
	public void setString(Object string) {
		String = string;
	}
}
