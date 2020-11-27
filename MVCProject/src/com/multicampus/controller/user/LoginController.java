package com.multicampus.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.multicampus.biz.user.UserDAOJDBC;
import com.multicampus.biz.user.UserVO;

@Controller
public class LoginController{

	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo) throws Exception{
		//System.out.println(9/0);
		
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
	public String Logout(HttpSession session) throws Exception {
		//로그아웃 요청한 브라우저에 해당하는 세션 객체를 강제 종료하고 메인 화면으로 이동한다.
		session.invalidate();
		return "forward:index.jsp";
	}

}


/*public class LoginController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("로그인 인증 처리");
		
		// 1. 사용자 입력정보 추출
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 2. DB 연동 처리
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAOJDBC userDAO = new UserDAOJDBC();
		UserVO user = userDAO.getUser(vo);
		
		// 3. 화면네비게이션
		ModelAndView max = new ModelAndView();
		if(user != null) {
			// 로그인 성공시, 상태 정보를 세션에 등록한다.
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			max.setViewName("redirect:getBoardList.do");//url 안 바뀌고 속도 빠름
		} else {
			max.setViewName("forward:login.jsp");//요청 응답 2번  url 바뀌지만 느리다.
		}
		
		return max;
	}

}
*/