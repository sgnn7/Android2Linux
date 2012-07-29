package org.sgnn7.android2linux.shell;

public class CommandResult {
	boolean hadErrors;
	String output;

	public CommandResult(int exitCode, String output) {
		this.hadErrors = exitCode != 0;
		this.output = output;
	}

	public boolean hadErrors() {
		return hadErrors;
	}

	public String getOutput() {
		return output;
	}

	@Override
	public String toString() {
		return getOutput();
	}
}
