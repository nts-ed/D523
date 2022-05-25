package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieLesson
 */
@WebServlet("/sample")
public class CookieLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name="";
		String email="";
		Cookie[] cookies=request.getCookies();
		if(cookies !=null) {
			for(Cookie  c:cookies) {
				if(c.getName().equals("name")) {
					name=URLDecoder.decode(c.getValue(),"UTF-8");
				}
				if(c.getName().equals("email")) {
					email=URLDecoder.decode(c.getValue(),"UTF-8");
				}
				if(!name.isEmpty() && !email.isEmpty()) {
					break;
				}
			}
		}
		
		request.setAttribute("name", name);
		request.setAttribute("email", email);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/form.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		
		Cookie[] cookies= {new Cookie("name",URLEncoder.encode(name,"UTF-8")),new Cookie("email",URLEncoder.encode(email,"UTF-8"))};
		for(Cookie c:cookies) {
			c.setMaxAge(60*60*24*180);
			response.addCookie(c);
		}
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/result.jsp");
		rd.forward(request, response);
	}

}
