package org.sgnn7.android2linux.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class FilesizeChooserView extends SeekBar {
	public FilesizeChooserView(Context context) {
		super(context);

		initialize(context);
	}

	public FilesizeChooserView(Context context, AttributeSet attrs) {
		super(context, attrs);

		initialize(context);
	}

	private void initialize(Context context) {
		setMax(4096);
		setProgress(2048);
	}
}
