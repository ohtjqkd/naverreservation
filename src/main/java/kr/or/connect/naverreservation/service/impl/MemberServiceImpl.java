package kr.or.connect.naverreservation.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import kr.or.connect.naverreservation.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Override
	public void logIn(String memberMail, HttpSession session) {
		// TODO Auto-generated method stub
		session.setAttribute("memberEmail", memberMail);
		session.getAttribute("memberEmail");
	}

}
