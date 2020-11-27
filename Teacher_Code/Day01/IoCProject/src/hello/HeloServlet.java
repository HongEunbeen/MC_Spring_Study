package hello;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HeloServlet() {
    	System.out.println("===> HeloServlet 생성");
    }

	protected void doGet(HttpServletRequest request, 
			             HttpServletResponse response) throws ServletException, IOException {
		System.out.println("===> doGet() 메소드 호출");
	}

}
