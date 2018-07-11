package com.example.gauravsingh.myapplication.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.example.gauravsingh.myapplication.R;

public class SuperTextView extends AppCompatTextView {

	public SuperTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}
	
	public SuperTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
		
		
	}
	
	public SuperTextView(Context context) {
		super(context);
		init(null);
	}
	
	private void init(AttributeSet attrs) {
		if (attrs!=null) {
			 TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SuperTextView);
			 String fontName = a.getString(R.styleable.SuperTextView_fontName);
			 if (fontName!=null) {
				 Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/"+fontName);
				 if(myTypeface!=null)
				 setTypeface(myTypeface);
			 }
			 a.recycle();
		}

	}

}