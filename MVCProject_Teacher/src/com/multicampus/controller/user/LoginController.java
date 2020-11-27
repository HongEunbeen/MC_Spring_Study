package com.multicampus.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.multicampus.biz.user.UserDAOJDBC;
import com.multicampus.biz.user.UserVO;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo) throws Exception {
		vo.setId("test");
		vo.setPassword("test");
		return "login";
	}

	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, UserDAOJDBC userDAO, HttpSession session) throws Exception {
		UserVO user = userDAO.getUser(vo);
		if(user != null) {
			session.setAttribute("user", user);			
			return "forward:getBoardList.do";
		} else {
			return "login";
		}
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) throws Exception {
		// 로그아웃 요청한 브라우저에 해당하는 세션 객체를 강제 종료하고 메인 화면으로 이동한다.
		session.invalidate();
		return "forward:index.jsp";	
	}

}
