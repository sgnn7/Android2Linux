package org.sgnn7.android2linux;

import org.sgnn7.android2linux.shell.ShellHelper;
import org.sgnn7.android2linux.shell.ShellLauncher;
import org.sgnn7.android2linux.widgets.FilesizeChooserView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final TextView output_text = (TextView) findViewById(R.id.output);
		output_text.setText(ShellLauncher.invoke("busybox --list").toString());

		final FilesizeChooserView filesizeChooser = (FilesizeChooserView) findViewById(R.id.file_size);
		findViewById(R.id.create_file).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShellHelper.createFile("blah.test", filesizeChooser.getProgress());
			}
		});

		findViewById(R.id.mount_loop_file).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShellHelper.mountLoopFile("blah.test");
			}
		});

		findViewById(R.id.format_file).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShellHelper.formatFile("blah.test");
			}
		});

		findViewById(R.id.mount_filesystem).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShellHelper.mountFilesystem();
			}
		});

		findViewById(R.id.install_bootstrapper).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShellHelper.installBootstrapper();
			}
		});
	}
}
