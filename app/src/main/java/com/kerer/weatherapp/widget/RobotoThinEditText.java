package com.kerer.weatherapp.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Date: 14.02.17
 * Time: 20:31
 *
 * @author Ivan Kerer
 */

public class RobotoThinEditText extends EditText {
    public RobotoThinEditText(Context context) {
        super(context);
        init();
    }

    public RobotoThinEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoThinEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RobotoThinEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_thin.ttf");
        super.setTypeface(tf, style);
    }

    @Override
    public void setTypeface(Typeface tf) {
        tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_thin.ttf");
        super.setTypeface(tf);
    }

    private void init() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_thin.ttf");
        this.setTypeface(font);
    }
}
