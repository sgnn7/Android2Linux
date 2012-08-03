package org.sgnn7.android2linux;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.sgnn7.android2linux.shell.ExecutionFailureResult;

public class ExecutionFailureResultTest {
	@Test
	public void hadErrorsIsAlwaysTrue() throws Exception {
		assertTrue(new ExecutionFailureResult(new Exception()).hadErrors());
		assertTrue(new ExecutionFailureResult(new Exception()).hadErrors());
		assertTrue(new ExecutionFailureResult(new Exception()).hadErrors());
	}

	@Test
	public void ouputIsRetrievedFromError() throws Exception {
		String expectedOuput = UUID.randomUUID().toString();
		Exception exception = new Exception(expectedOuput);

		assertEquals(expectedOuput, new ExecutionFailureResult(exception).getOutput());
	}

	@Test
	public void ouputCanRetrievedFromToString() throws Exception {
		String expectedOuput = UUID.randomUUID().toString();
		Exception exception = new Exception(expectedOuput);

		assertEquals(expectedOuput, new ExecutionFailureResult(exception).toString());
	}

}
