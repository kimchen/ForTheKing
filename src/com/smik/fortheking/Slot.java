package com.smik.fortheking;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class Slot extends Button{
	public static final int MAX_NUMER = 13;
	public Slot(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setFocusable(false);
		this.setMinWidth(72);
	}
	public Slot(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setFocusable(false);
		this.setMinWidth(72);
	}
	public Slot(Context context) {
		super(context);
		this.setFocusable(false);
		this.setMinWidth(72);
	}
	
	public boolean isEmpty = true;
	public SlotType type = SlotType.SPADES;
	public int number = 1;
}
