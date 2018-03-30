package com.netcommon.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{
	/**
	 * 详解servlet
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		//获取请求参数
		String name = request.getParameter("name");
		System.out.println("req : "+name);
		String hello = "hello : "+name;
		//保存到请求对象的值域
		req.setAttribute("hello",hello);
		request.getRequestDispatcher("helloServlet.jsp").forward(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("########Servlet_destroy#######");
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("#######Servlet_init########");
	}
	
	
}
