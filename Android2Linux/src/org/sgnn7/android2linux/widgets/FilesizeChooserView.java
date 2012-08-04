package org.sgnn7.android2linux.widgets;

import org.sgnn7.android2linux.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

public class FilesizeChooserView extends RelativeLayout {
	private ImageView sliderBackground;
	private ImageView sliderButton;

	public FilesizeChooserView(Context context) {
		super(context);

		initialize(context);
	}

	public FilesizeChooserView(Context context, AttributeSet attrs) {
		super(context, attrs);

		initialize(context);
	}

	private void initialize(Context context) {
		sliderBackground = new ImageView(context);
		sliderBackground.setImageDrawable(context.getResources().getDrawable(R.drawable.slider_bg));
		sliderBackground.setScaleType(ScaleType.CENTER_INSIDE);
		sliderBackground.setAdjustViewBounds(true);

		sliderButton = new ImageView(context);
		sliderButton.setImageDrawable(context.getResources().getDrawable(R.drawable.slider_button));
		sliderBackground.setScaleType(ScaleType.CENTER_INSIDE);
		sliderButton.setAdjustViewBounds(true);

		LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		layoutParams.setMargins(0, 6, 0, 0);
		addView(sliderBackground, layoutParams);
		addView(sliderButton, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

	}
}
