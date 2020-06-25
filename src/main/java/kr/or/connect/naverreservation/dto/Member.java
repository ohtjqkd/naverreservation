package kr.or.connect.naverreservation.dto;

public class Member {
	private String memberEmail;

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	@Override
	public String toString() {
		return "Member [memberEmail=" + memberEmail + "]";
	}
	
}
