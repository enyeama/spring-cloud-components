package com.example.aop.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transaction {

	Logger LOGGER = LoggerFactory.getLogger(Transaction.class);

	public void start(String methodName) {
		LOGGER.info("Start transaction before {} invoked", methodName);
	}

	public void commit(String methodName) {
		LOGGER.info("Commit transaction before {} invoked", methodName);
	}

	public void rollback(String methodName) {
		LOGGER.info("Rollback transaction {}", methodName);
	}

}
