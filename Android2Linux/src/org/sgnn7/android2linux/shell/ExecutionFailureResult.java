package org.sgnn7.android2linux.shell;

public class ExecutionFailureResult extends CommandResult {
	public ExecutionFailureResult(Exception e) {
		super(-1, e.getMessage());
	}
}
