package com.example.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Future;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.service.AsyncEjbTaskExecutor;
//http://localhost:8080/ServletThree
@WebServlet("/ServletThree")
public class ServletThree extends HttpServlet {
	/**
	 * 
	 * 
	 * java:app/module-ejb-1.0-SNAPSHOT/ExampleService!com.example.service.ExampleService
	 * java:module/ExampleService!com.example.service.ExampleService
	 * ejb:module-ear-1.0-SNAPSHOT/module-ejb-1.0-SNAPSHOT/ExampleService!com.example.service.ExampleService
	 * java:global/module-ear-1.0-SNAPSHOT/module-ejb-1.0-SNAPSHOT/ExampleService
	 * java:app/module-ejb-1.0-SNAPSHOT/ExampleService java:module/ExampleService
	 * 
	 * 
	 * 
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private AsyncEjbTaskExecutor asyncEjbTaskExecutor;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store,no-cache,private,must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 1); // prevents caching at the proxy
												// server
		response.setContentType("text/html");
		final PrintWriter writer = response.getWriter();
		try {

			final Context context;
			try {
				context = new InitialContext();
				asyncEjbTaskExecutor = (AsyncEjbTaskExecutor) context.lookup(
						"java:global/module-ear-1.0-SNAPSHOT/module-ejb-1.0-SNAPSHOT/AsyncEjbTaskExecutor!com.example.service.AsyncEjbTaskExecutor");

			} catch (NamingException e) {
				e.printStackTrace();
			}

			Future<Boolean> execute = asyncEjbTaskExecutor.execute();
			while (!execute.isDone()) {
				System.out.println("Working...");
			}

			writer.println("<h2> ServletThree </h2>");
			writer.println(execute.get());

		} catch (Throwable ex) {

			writer.println("<h1>NOK</h1>");
			writer.println(ex);
		} finally {
			writer.flush();
		}
	}
}