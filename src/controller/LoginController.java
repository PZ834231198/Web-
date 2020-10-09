package controller;

import dao.UserDao;
import vo.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class LoginController
 */

@WebServlet(urlPatterns = "/Login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		//设置编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		HttpSession session = request.getSession();

		String vCode = (String) session.getAttribute("vCode");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String userCode = request.getParameter("userCode");
		String checkBox = request.getParameter("autologin");

		UserDao userDao = new UserDao();
		User user = userDao.findUserByUserName(userName);
		session.setAttribute("currentUserName",user.getUserName());

		String errorInfo = "";
		if(!vCode.equalsIgnoreCase(userCode)){ //验证码有误
			errorInfo = "验证码有误";
		}
		else{
			if(user == null) { //用户名不存在
				errorInfo = "用户名不存在";
			}else{ //用户名存在
				if(!password.equals(user.getPassWord())) { //密码不正确
					errorInfo = "密码不正确";
				}
			}
		}

		if(!errorInfo.equals("")){
			request.setAttribute("errorInfo",errorInfo);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
			requestDispatcher.forward(request,response);
		}else{  //允许登录
			//response.addHeader("Set-Cookie","cookie中的内容");
			Cookie cookieUserName = new Cookie("userName", user.getUserName());
			Cookie cookiePassword = new Cookie("password", user.getPassWord());
			if("on".equals(checkBox)) {  //是否勾选“一周免登陆”
				cookieUserName.setMaxAge(60 * 60 * 24 * 7);
				cookiePassword.setMaxAge(60 * 60 * 24 * 7);
			}
			else
			{  //会话结束就销毁
				cookieUserName.setMaxAge(0);
				cookiePassword.setMaxAge(0);
			}
			response.addCookie(cookieUserName);
			response.addCookie(cookiePassword);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("main.jsp");
			requestDispatcher.forward(request,response);
		}
	}

}
