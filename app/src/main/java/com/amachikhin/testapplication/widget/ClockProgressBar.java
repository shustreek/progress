package com.amachikhin.testapplication.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.amachikhin.testapplication.R;
import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;


/**
 * Прогресс в виде многоугольника
 */
public class ClockProgressBar extends FrameLayout {

    private static final int SIZE_NORMAL = 0;
    private static final int SIZE_LARGE = 1;

    private VectorChildFinder vector;
    private VectorDrawableCompat.VFullPath progress;
    private ImageView progressBar;
    private ImageView iconView;
    private TextView title;
    private TextView value;

    public ClockProgressBar(@NonNull Context context) {
        super(context);
        initLayout(context);
        initAttrs(null, 0, 0);
    }

    public ClockProgressBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
        initAttrs(attrs, 0, 0);
    }

    public ClockProgressBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
        initAttrs(attrs, defStyleAttr, 0);
    }

    public ClockProgressBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initLayout(context);
        initAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initLayout(@NonNull Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_clock_progress_bar, this);

        progressBar = view.findViewById(R.id.progress_bar);
        iconView = view.findViewById(R.id.icon);
        title = view.findViewById(R.id.title);
        value = view.findViewById(R.id.value);

    }

    private void initAttrs(AttributeSet attrs, int defStyle, int defStyleRes) {
        final TypedArray typedArray = getContext().getTheme()
                .obtainStyledAttributes(attrs, R.styleable.ClockProgressBar, defStyle, defStyleRes);
        final int color = typedArray.getColor(R.styleable.ClockProgressBar_cpb_color, getResources().getColor(R.color.greenTeal));
        final int icon = typedArray.getResourceId(R.styleable.ClockProgressBar_cpb_icon, 0);
        final float progress = typedArray.getFloat(R.styleable.ClockProgressBar_cpb_progress, 0f);
        final String title = typedArray.getString(R.styleable.ClockProgressBar_cpb_title);
        final String value = typedArray.getString(R.styleable.ClockProgressBar_cpb_value);
        final int textColor = typedArray.getColor(R.styleable.ClockProgressBar_cpb_text_color, this.title.getTextColors().getDefaultColor());
        final int size = typedArray.getInt(R.styleable.ClockProgressBar_cpb_size, SIZE_NORMAL);
        if (size == SIZE_LARGE) {
            int progressSize = getResources().getDimensionPixelSize(R.dimen.cpb_progress_size_large);
            int iconSize = getResources().getDimensionPixelSize(R.dimen.cpb_icon_size_large);
            ViewGroup.LayoutParams params = progressBar.getLayoutParams();
            params.height = progressSize;
            params.width = progressSize;
            progressBar.setLayoutParams(params);

            params = iconView.getLayoutParams();
            params.height = iconSize;
            params.width = iconSize;
            iconView.setLayoutParams(params);
        }
        typedArray.recycle();

        createVector();

        setColor(color);
        setProgress(progress);
        setIcon(icon);
        setTitle(title);
        setValue(value);
        setColorTint(textColor);
    }

    private void createVector() {
        vector = new VectorChildFinder(getContext(), R.drawable.cpb_progress_polygon, progressBar);
        progress = vector.findPathByName("progress");
    }

    public void setColorRes(@ColorRes int colorId) {
        setColor(getResources().getColor(colorId));
    }

    public void setColor(@ColorInt int color) {
        progress.setStrokeColor(color);
    }

    public void setProgress(float progress) {
        setProgress(progress, 100);
    }

    public void setProgress(float progress, float max) {
        this.progress.setTrimPathStart(progress >= max ? 0 : (1f - progress / max));
        progressBar.invalidate();
    }

    public void setIcon(@DrawableRes int drawableId) {
        iconView.setImageResource(drawableId);
    }

    public void setTitle(@StringRes int text) {
        title.setText(text);
    }

    public void setTitle(CharSequence text) {
        title.setText(text);
    }

    public void setValue(@StringRes int text) {
        value.setText(text);
    }

    public void setValue(CharSequence text) {
        value.setText(text);
    }

    public void setColorTintRes(@ColorRes int colorId) {

        title.setTextColor(getResources().getColor(colorId));
        value.setTextColor(getResources().getColor(colorId));
        progressBar.getBackground().setTint(getResources().getColor(colorId));
    }

    public void setColorTint(@ColorInt int color) {
        title.setTextColor(color);
        value.setTextColor(color);
        progressBar.getBackground().setTint(color);
    }
}
