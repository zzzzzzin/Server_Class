package com.test.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ex10Data extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String num1 = req.getParameter("num1");
		String num2 = req.getParameter("num2");
		String num3 = req.getParameter("num3");
		
		System.out.println(num1);
		System.out.println(num2);
		System.out.println(num3);
		
	}
}
