package kr.or.connect.naverreservation.service;

import javax.servlet.http.HttpSession;

public interface MemberService {
	public void logIn(String memberMail, HttpSession session);
}
