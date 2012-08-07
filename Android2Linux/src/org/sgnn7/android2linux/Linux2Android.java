package org.sgnn7.android2linux;

import android.app.Application;

public class Linux2Android extends Application {
	private static Linux2Android applicationInstance;

	public static Linux2Android getInstance() {
		return applicationInstance;
	}

	@Override
	public void onCreate() {
		applicationInstance = this;
		super.onCreate();
	}
}
