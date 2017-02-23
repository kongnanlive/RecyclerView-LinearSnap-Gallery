package com.ecarx.linearsnaptest;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by kongnan on 17/2/22.
 */
public class RecyclerGallery extends RecyclerView {

    private static final String TAG = RecyclerGallery.class.getSimpleName();

    public static final int SCALE_PIVOT_CENTER = 0;
    public static final int SCALE_PIVOT_TOP = 1;
    public static final int SCALE_PIVOT_BOTTOM = 2;

    private int mScalePivot = SCALE_PIVOT_BOTTOM;
    private float mSelectedScale = 0.7f;

    private int[] viewLocation = new int[2];

    public RecyclerGallery(Context context) {
        super(context);
    }

    public RecyclerGallery(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerGallery(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
         /* Center Zoom out */
//        if (mSelectedScale != 1.f) {
//            int childWidth = child.getWidth();
//            int childHeight = child.getHeight();
//
//            final int center = getCenterOfGallery();
//            final int childCenter = child.getLeft() + childWidth / 2;
//            final int offsetCenter = Math.abs(center - childCenter);
//            final float offsetScale = (childWidth - offsetCenter) * -1.f / childWidth;
//            if (offsetCenter < childWidth) {
//                if (mSelectedScale != 1.f) {
//                    float pivotY = 0;
//                    if (mScalePivot == SCALE_PIVOT_CENTER) {
//                        pivotY = (childHeight + child.getPaddingTop() - child.getPaddingBottom()) / 2;
//                    } else if (mScalePivot == SCALE_PIVOT_TOP) {
//                        pivotY = child.getPaddingTop();
//                    } else if (mScalePivot == SCALE_PIVOT_BOTTOM) {
//                        pivotY = childHeight - child.getPaddingBottom();
//                    }
//                    float scale = 1 + (mSelectedScale - 1) * offsetScale;
//                    child.setPivotX(childWidth / 2.f);
//                    child.setPivotY(pivotY);
//                    child.setScaleX(scale);
//                    child.setScaleY(scale);
//                }
//            } else {
//                if (mSelectedScale != 1.f) {
//                    child.setScaleX(1.f);
//                    child.setScaleY(1.f);
//                }
//            }
//        }

        /* Center Zoom in */
         // use this
//        child.getLocationInWindow(viewLocation);
//        int x = viewLocation[0];
        // or this is ok
        int x = child.getLeft();

        // view's width and height
        int vWidth = child.getWidth() - child.getPaddingLeft() - child.getPaddingRight();
        int vHeight = child.getHeight() - child.getPaddingTop() - child.getPaddingBottom();
        // device's width
        int dWidth = getResources().getDisplayMetrics().widthPixels;

        if (vWidth >= dWidth) {
            // Do nothing if imageView's width is bigger than device's width.
            Log.d(TAG, "return");
            return super.drawChild(canvas, child, drawingTime);
        }

        float scale;
        int pivot = (dWidth - vWidth) / 2;
        if (x <= pivot) {
            scale = 2 * (1 - mSelectedScale) * (x + vWidth) / (dWidth + vWidth) + mSelectedScale;
        } else {
            scale = 2 * (1 - mSelectedScale) * (dWidth - x) / (dWidth + vWidth) + mSelectedScale;
        }

        child.setPivotX(vWidth / 2);
        child.setPivotY(vHeight / 2);
        child.setScaleX(scale);
        child.setScaleY(scale);

        return super.drawChild(canvas, child, drawingTime);
    }

    /**
     * @return The center of this Gallery.
     */
    protected int getCenterOfGallery() {
        int paddingLeft = getPaddingLeft();
        return (getWidth() - paddingLeft - getPaddingRight()) / 2 + paddingLeft;
    }

    /**
     * 设置选中的 Item 缩放的比例。
     *
     * @param scale 缩放的比例
     */
    public void setSelectedScale(float scale) {
        this.mSelectedScale = scale;
        invalidate();
    }

    /**
     * 设置缩放的中心点位置。
     *
     * @param pivot 缩放的中心点位置
     * @see RecyclerGallery#SCALE_PIVOT_CENTER
     * @see RecyclerGallery#SCALE_PIVOT_TOP
     * @see RecyclerGallery#SCALE_PIVOT_BOTTOM
     */
    public void setScalePivot(int pivot) {
        if (pivot != SCALE_PIVOT_BOTTOM && pivot != SCALE_PIVOT_CENTER && pivot != SCALE_PIVOT_TOP) {
            throw new RuntimeException("The scale pivot must be one of SCALE_PIVOT_BOTTOM、" +
                    "SCALE_PIVOT_CENTER or SCALE_PIVOT_TOP");
        }

        this.mScalePivot = pivot;
        invalidate();
    }
}
