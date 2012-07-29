package org.sgnn7.android2linux;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.sgnn7.android2linux.shell.CommandResult;

public class CommandResultTest {
	@Test
	public void hadErrorsIsCalculatedFromResultCode() throws Exception {
		assertFalse(new CommandResult(0, "aaa").hadErrors());

		assertTrue(new CommandResult(-1, "bbb").hadErrors());
		assertTrue(new CommandResult(1, "ccc").hadErrors());
		assertTrue(new CommandResult(127, "ddd").hadErrors());
	}

	@Test
	public void ouputIsRetrievedFromConstructor() throws Exception {
		String expectedOuput = UUID.randomUUID().toString();
		assertEquals(expectedOuput, new CommandResult(0, expectedOuput).getOutput());
	}

	@Test
	public void ouputCanRetrievedFromToString() throws Exception {
		String expectedOuput = UUID.randomUUID().toString();
		assertEquals(expectedOuput, new CommandResult(0, expectedOuput).toString());
	}

}
