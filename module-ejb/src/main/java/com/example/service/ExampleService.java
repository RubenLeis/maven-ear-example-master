package com.example.service;

import java.util.concurrent.Future;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class ExampleService {

	private AsyncEjbTaskExecutor asyncEjbTaskExecutor;

	public String whoAmI() {
		return "i'm ExampleService v2";
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String transactionalMethod() {
		final Context context;
		try {
			context = new InitialContext();
			asyncEjbTaskExecutor = (AsyncEjbTaskExecutor) context.lookup(
					"java:global/module-ear-1.0-SNAPSHOT/module-ejb-1.0-SNAPSHOT/AsyncEjbTaskExecutor!com.example.service.AsyncEjbTaskExecutor");

		} catch (NamingException e) {
			throw new RuntimeException(e);
		}

		Future<Boolean> execute = asyncEjbTaskExecutor.execute();
		while (!execute.isDone()) {
			System.out.println("Working...");
		}
		return "hi";
	}

}
