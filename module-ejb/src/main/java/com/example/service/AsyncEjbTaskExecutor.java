package com.example.service;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
public class AsyncEjbTaskExecutor {

	// @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Future<Boolean> execute() {

		return new AsyncResult<Boolean>(sleep());
	}

	private Boolean sleep() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

}
