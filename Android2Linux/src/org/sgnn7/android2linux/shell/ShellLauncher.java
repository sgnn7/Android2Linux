package org.sgnn7.android2linux.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.sgnn7.android2linux.util.LogMe;

//TODO test me
public class ShellLauncher {

	public static CommandResult invoke(String command) {
		return invoke(command, false);
	}

	public static CommandResult invoke(String command, boolean superUser) {
		{
			int result = -1;
			String output = "";

			Runtime run = Runtime.getRuntime();
			Process pr = null;
			try {
				// TODO find where busybox is
				ArrayList<String> commandList = new ArrayList<String>();
				if (superUser) {
					commandList.add("su");
					commandList.add("-c");
					commandList.add("/system/bin/busybox " + command);
				} else {
					commandList.add("/system/bin/busybox");
					commandList.addAll(Arrays.asList(command.split(" ")));
				}

				LogMe.e("Running: " + commandList);
				pr = run.exec(commandList.toArray(new String[0]));

			} catch (IOException e) {
				LogMe.e("Could not execute command", e);
				return new ExecutionFailureResult(e);
			}

			try {
				result = pr.waitFor();
			} catch (InterruptedException e) {
				LogMe.e("Could not finish command", e);
				return new ExecutionFailureResult(e);
			}

			LogMe.e("result: " + result);

			InputStreamReader isr = new InputStreamReader(pr.getInputStream());
			BufferedReader buf = new BufferedReader(isr);

			try {
				output = IOUtils.toString(buf);
				LogMe.e(output);
			} catch (IOException e) {
				LogMe.e("Could not read output", e);
				return new ExecutionFailureResult(e);
			}

			return new CommandResult(result, output);
		}
	}
}
