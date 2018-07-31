package ru.panmin.gtspro.ui.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import ru.panmin.gtspro.R;

public class VectorsSupportTextView extends AppCompatTextView {

    public VectorsSupportTextView(Context context) {
        super(context);
    }

    public VectorsSupportTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public VectorsSupportTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.VectorsSupportTextView);

            Drawable drawableLeft = null;
            Drawable drawableRight = null;
            Drawable drawableBottom = null;
            Drawable drawableTop = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawableLeft = attributeArray.getDrawable(R.styleable.VectorsSupportTextView_drawableLeftCompat);
                drawableRight = attributeArray.getDrawable(R.styleable.VectorsSupportTextView_drawableRightCompat);
                drawableBottom = attributeArray.getDrawable(R.styleable.VectorsSupportTextView_drawableBottomCompat);
                drawableTop = attributeArray.getDrawable(R.styleable.VectorsSupportTextView_drawableTopCompat);
            } else {
                final int drawableLeftId = attributeArray
                        .getResourceId(R.styleable.VectorsSupportTextView_drawableLeftCompat, -1);
                final int drawableRightId = attributeArray
                        .getResourceId(R.styleable.VectorsSupportTextView_drawableRightCompat, -1);
                final int drawableBottomId = attributeArray
                        .getResourceId(R.styleable.VectorsSupportTextView_drawableBottomCompat, -1);
                final int drawableTopId = attributeArray
                        .getResourceId(R.styleable.VectorsSupportTextView_drawableTopCompat, -1);

                if (drawableLeftId != -1) {
                    drawableLeft = AppCompatResources.getDrawable(context, drawableLeftId);
                }
                if (drawableRightId != -1) {
                    drawableRight = AppCompatResources.getDrawable(context, drawableRightId);
                }
                if (drawableBottomId != -1) {
                    drawableBottom = AppCompatResources.getDrawable(context, drawableBottomId);
                }
                if (drawableTopId != -1) {
                    drawableTop = AppCompatResources.getDrawable(context, drawableTopId);
                }
            }
            setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
            attributeArray.recycle();
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable left, @Nullable Drawable top,
                                                        @Nullable Drawable right, @Nullable Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

    public void setDrawableLeft(@Nullable Drawable drawable) {
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawablesWithIntrinsicBounds(drawable, drawables[1], drawables[2], drawables[3]);
    }

    public void setDrawableTop(@Nullable Drawable drawable) {
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawable, drawables[2], drawables[3]);
    }

    public void setDrawableRight(@Nullable Drawable drawable) {
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    public void setDrawableBottom(@Nullable Drawable drawable) {
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawable);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(@DrawableRes int left, @DrawableRes int top,
                                                        @DrawableRes int right, @DrawableRes int bottom) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        } else {
            Drawable drawableLeft = null;
            Drawable drawableRight = null;
            Drawable drawableBottom = null;
            Drawable drawableTop = null;
            if (left != 0) {
                drawableLeft = AppCompatResources.getDrawable(getContext(), left);
            }
            if (right != 0) {
                drawableRight = AppCompatResources.getDrawable(getContext(), right);
            }
            if (bottom != 0) {
                drawableBottom = AppCompatResources.getDrawable(getContext(), bottom);
            }
            if (top != 0) {
                drawableTop = AppCompatResources.getDrawable(getContext(), top);
            }
            setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
        }
    }

    public void setDrawableLeft(@DrawableRes int drawableRes) {
        @Nullable Drawable drawable = null;
        if (drawableRes != 0) {
            drawable = AppCompatResources.getDrawable(getContext(), drawableRes);
        }
        setDrawableLeft(drawable);
    }

    public void setDrawableTop(@DrawableRes int drawableRes) {
        @Nullable Drawable drawable = null;
        if (drawableRes != 0) {
            drawable = AppCompatResources.getDrawable(getContext(), drawableRes);
        }
        setDrawableTop(drawable);
    }

    public void setDrawableRight(@DrawableRes int drawableRes) {
        @Nullable Drawable drawable = null;
        if (drawableRes != 0) {
            drawable = AppCompatResources.getDrawable(getContext(), drawableRes);
        }
        setDrawableRight(drawable);
    }

    public void setDrawableBottom(@DrawableRes int drawableRes) {
        @Nullable Drawable drawable = null;
        if (drawableRes != 0) {
            drawable = AppCompatResources.getDrawable(getContext(), drawableRes);
        }
        setDrawableBottom(drawable);
    }

}