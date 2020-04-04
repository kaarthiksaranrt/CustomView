package com.example.dwis.toggleshap;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ShapeSelectorView extends View {
    private int shapeColor;
    private boolean displayShapeName;
    private int shapeWidth = 300;
    private int shapeHeight = 300;
    private int textXOffset = 0;
    private int textYOffset = 30;
    private Paint paintShape;
    private String[] shapeValues = { "square", "circle", "triangle" };
    private int currentShapeIndex = 0;
// We must provide a constructor that takes a Context and an AttributeSet.
    // This constructor allows the UI to create and edit an instance of your view.
    public ShapeSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(attrs);
    }
    private void initAttributes(AttributeSet attrs) {
// Obtain a typed array of attributes
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ShapeSelectorView, 0, 0);
// Extract custom attributes into member variables
        try {
            shapeColor = a.getColor(R.styleable.ShapeSelectorView_shapeColor, Color.BLACK);
            displayShapeName = a.getBoolean(R.styleable.ShapeSelectorView_displayShapeName, false);
        } finally {
// TypedArray objects are shared and must be recycled.
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
// Defines the extra padding for the shape name text
        int textPadding = 10;
        int contentWidth = shapeWidth;
// Resolve the width based on our minimum and the measure spec
        int minw = contentWidth + getPaddingLeft() + getPaddingRight();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 0);
// Ask for a height that would let the view get as big as it can
        int minh = shapeHeight + getPaddingBottom() + getPaddingTop();
        if (displayShapeName) {
            minh += textYOffset + textPadding;
        }
        int h = resolveSizeAndState(minh, heightMeasureSpec, 0);
// Calling this method determines the measured width and height
// Retrieve with getMeasuredWidth or getMeasuredHeight methods later
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        paintShape = new Paint();
        paintShape.setStyle(Paint.Style.FILL);
        paintShape.setColor(shapeColor);
        paintShape.setTextSize(30);
        String shapeSelected = shapeValues[currentShapeIndex];
        if (shapeSelected.equals("square")) {
            canvas.drawRect(0, 0, shapeWidth, shapeHeight, paintShape);
            textXOffset = 0;
            Log.d("square", String.valueOf(currentShapeIndex));
        } else if (shapeSelected.equals("circle")) {
            canvas.drawCircle(shapeWidth / 2, shapeHeight / 2,
                    shapeWidth / 2, paintShape);
            textXOffset = 12;
            Log.d("Circle", String.valueOf(currentShapeIndex));
        } else if (shapeSelected.equals("triangle")) {
            Path trianglePath = new Path();
            trianglePath.moveTo(0, shapeHeight);
            trianglePath.lineTo(shapeWidth, shapeHeight);
            trianglePath.lineTo(shapeWidth / 2, 0);
            canvas.drawPath(trianglePath, paintShape);
            textXOffset = 0;
        }
        if (displayShapeName) {
            canvas.drawText(shapeSelected, shapeWidth + textXOffset,
                    shapeHeight + textXOffset, paintShape);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currentShapeIndex = (++currentShapeIndex) %
                    shapeValues.length;
            Log.d("Shape", String.valueOf(currentShapeIndex));
            postInvalidate();
            return true;
        }
        return result;
    }

    public String getSelectedShape() {
        return shapeValues[currentShapeIndex];
    }
}
