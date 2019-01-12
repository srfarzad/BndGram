package com.app.bndlibrary.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by farzad.sarseify on 09/02/2017.
 */

public class DroidTextView extends AppCompatTextView {
    public DroidTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setType( context);
    }

    public DroidTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setType( context);
    }

    public DroidTextView(Context context) {
        super(context);
        setType( context);
    }

    public void setType(Context context){

        Typeface typeface=Typeface.createFromAsset(context.getAssets(),"fonts/IRANSansMobile_Black.ttf");

        setTypeface(typeface);


    }

}
