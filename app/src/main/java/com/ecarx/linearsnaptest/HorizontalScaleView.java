package com.ecarx.linearsnaptest;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * Created by kongnan on 17/2/22.
 */
public class HorizontalScaleView extends FrameLayout implements ViewTreeObserver.OnScrollChangedListener {

    public static final String TAG = HorizontalScaleView.class.getSimpleName();

    private int[] viewLocation = new int[2];

    private float finalScaleRatio = 0.7f;

    public HorizontalScaleView(@NonNull Context context) {
        super(context);
        init();
    }


    public HorizontalScaleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalScaleView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);
    }

    public void setFinalScaleRatio(float scale) {
        finalScaleRatio = scale;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        getLocationInWindow(viewLocation);
        int x = viewLocation[0];

        // view's width and height
        int vWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int vHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        // device's width
        int dWidth = getResources().getDisplayMetrics().widthPixels;

        if (vWidth >= dWidth) {
            // Do nothing if imageView's width is bigger than device's width.
            Log.d(TAG, "return");
            return;
        }

        float scale;
        int pivot = (dWidth - vWidth) / 2;
        if (x <= pivot) {
            scale = 2 * (1 - finalScaleRatio) * (x + vWidth) / (dWidth + vWidth) + finalScaleRatio;
        } else {
            scale = 2 * (1 - finalScaleRatio) * (dWidth - x) / (dWidth + vWidth) + finalScaleRatio;
        }

        canvas.scale(scale, scale, vWidth / 2, vHeight / 2);
        Log.d(TAG, "scale:" + scale);
        super.onDraw(canvas);
    }

    @Override
    public void onScrollChanged() {
        invalidate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnScrollChangedListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        getViewTreeObserver().removeOnScrollChangedListener(this);
        super.onDetachedFromWindow();
    }

}
