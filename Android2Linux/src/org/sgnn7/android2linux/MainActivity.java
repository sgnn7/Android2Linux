package org.sgnn7.android2linux;

import org.sgnn7.android2linux.shell.ShellLauncher;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView output_text = (TextView) findViewById(R.id.output);
        output_text.setText(ShellLauncher.invoke("ls").toString());
    }
}
