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
		//�α׾ƿ� ��û�� �������� �ش��ϴ� ���� ��ü�� ���� �����ϰ� ���� ȭ������ �̵��Ѵ�.
		session.invalidate();
		return "forward:index.jsp";
	}

}


/*public class LoginController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("�α��� ���� ó��");
		
		// 1. ����� �Է����� ����
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 2. DB ���� ó��
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAOJDBC userDAO = new UserDAOJDBC();
		UserVO user = userDAO.getUser(vo);
		
		// 3. ȭ��׺���̼�
		ModelAndView max = new ModelAndView();
		if(user != null) {
			// �α��� ������, ���� ������ ���ǿ� ����Ѵ�.
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			max.setViewName("redirect:getBoardList.do");//url �� �ٲ�� �ӵ� ����
		} else {
			max.setViewName("forward:login.jsp");//��û ���� 2��  url �ٲ����� ������.
		}
		
		return max;
	}

}
*/