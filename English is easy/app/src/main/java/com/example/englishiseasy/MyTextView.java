package com.example.englishiseasy;

/**
 * Created by Дмитрий on 27.02.2016.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setCustomFont(context, attributeSet);
    }

    public MyTextView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        setCustomFont(context, attributeSet);
    }

    private void setCustomFont(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MyTextView);
        String customFont = typedArray.getString(R.styleable.MyTextView_customFont);
        setCustomFont(context, customFont);
        typedArray.recycle();
    }

    public void setCustomFont(Context context, String asset) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), asset);
        setTypeface(typeface);
    }

}