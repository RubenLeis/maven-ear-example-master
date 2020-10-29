package com.example.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletOne")
public class ServletOne extends HttpServlet {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store,no-cache,private,must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 1); // prevents caching at the proxy
												// server
		response.setContentType("text/html");

		final PrintWriter writer = response.getWriter();

		try {

			writer.println("<h2> ServletOne </h2>");

		} catch (Throwable ex) {

			writer.println("<h1>NOK</h1>");
			writer.println(ex);
		} finally {
			writer.flush();
		}
	}
}