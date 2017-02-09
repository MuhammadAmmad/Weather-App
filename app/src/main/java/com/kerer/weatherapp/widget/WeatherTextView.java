package com.kerer.weatherapp.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Date: 09.02.17
 * Time: 2:18
 *
 * @author Ivan Kerer
 */

public class WeatherTextView extends TextView {
    public WeatherTextView(Context context) {
        super(context);
        init();
    }

    public WeatherTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WeatherTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public WeatherTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/weathericons_font.ttf");
        super.setTypeface(tf, style);
    }

    @Override
    public void setTypeface(Typeface tf) {
        tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/weathericons_font.ttf");
        super.setTypeface(tf);
    }

    private void init() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/weathericons_font.ttf");
        this.setTypeface(font);
    }
}
