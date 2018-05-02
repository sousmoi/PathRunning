package wholam.path.drawable;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;

import static android.animation.ValueAnimator.INFINITE;
import static android.animation.ValueAnimator.RESTART;
import static android.graphics.PixelFormat.OPAQUE;

/**
 * created by sfx on 2018/4/19.
 */

public abstract class AbsPathRunDrawable extends Drawable implements Animatable {
    private final Paint mPaint = new Paint();
    private final int stroke;
    private final Path mBgPath = new Path();

    private ValueAnimator animator;
    private final int stokeColor;

    AbsPathRunDrawable(int stroke, int stokeColor) {
        this.stroke = stroke;
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(stroke);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setAntiAlias(true);
        this.stokeColor = stokeColor;
    }


    private PathMeasure pathMeasure;
    private float pathLength;

    private final Path leftPath = new Path();
    private LinearGradient leftGradient;
    private final Path rightPath = new Path();
    private LinearGradient rightGradient;


    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mBgPath.reset();
        drawPath(mBgPath, left, top, right, bottom, stroke);
        pathMeasure = new PathMeasure();
        pathMeasure.setPath(mBgPath, false);
        pathLength = pathMeasure.getLength();

        animator = ValueAnimator.ofInt(0, 125);
        animator.setDuration(2000);
        animator.setRepeatCount(INFINITE);
        animator.setRepeatMode(RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private float lastValue = 0;
            private float dValue = pathLength / 90;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (isDrawing) return;
                lastValue += dValue;
                lastValue = lastValue % pathLength;
                update(lastValue);
                invalidateSelf();

            }
        });
        update(0);
    }

    private void update(float value) {
        float lineLength = pathLength / 2;
        leftRunLine(lineLength, value);
        rightRunLine(lineLength, (value + pathLength / 2) % pathLength);
    }

    protected abstract void drawPath(Path bgPath, int left, int top, int right, int bottom, final int stroke);

    //
    private void rightRunLine(float lineLength, float factor) {
        float dFactor = factor + lineLength - pathLength;
        float[] startPoint = new float[2];
        float[] endPoint = new float[2];
        pathMeasure.getPosTan(factor, endPoint, null);
        final float endDistance = (factor + lineLength) % pathLength;
        pathMeasure.getPosTan(endDistance, startPoint, null);

        rightPath.reset();
        if (dFactor <= 0) {
            pathMeasure.getSegment(factor, factor + lineLength, rightPath, true);
        } else {
            pathMeasure.getSegment(0, dFactor, rightPath, true);
            pathMeasure.getSegment(factor, pathLength, rightPath, true);
        }
        rightGradient = new LinearGradient(startPoint[0], startPoint[1]
                , endPoint[0], endPoint[1]
                , new int[]{Color.WHITE, Color.parseColor("#00FFFFFF")}
                , new float[]{0f, 0.8f}
                , Shader.TileMode.CLAMP);
    }

    //
    private void leftRunLine(float lineLength, float factor) {
        float dFactor = factor + lineLength - pathLength;
        float[] startPoint = new float[2];
        float[] endPoint = new float[2];
        pathMeasure.getPosTan(factor, endPoint, null);
        final float endDistance = (factor + lineLength) % pathLength;
        pathMeasure.getPosTan(endDistance, startPoint, null);
        //
        leftPath.reset();
        if (dFactor <= 0) {
            pathMeasure.getSegment(factor, factor + lineLength, leftPath, true);
        } else {
            pathMeasure.getSegment(0, dFactor, leftPath, true);
            pathMeasure.getSegment(factor, pathLength, leftPath, true);
        }
        leftGradient = new LinearGradient(startPoint[0], startPoint[1]
                , endPoint[0], endPoint[1]
                , new int[]{Color.WHITE, Color.parseColor("#00FFFFFF")}
                , new float[]{0f, 0.8f}
                , Shader.TileMode.CLAMP);
    }


    private volatile boolean isDrawing = false;

    @Override
    public final void invalidateSelf() {
        isDrawing = true;
        super.invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public final void draw(@NonNull Canvas canvas) {
        canvas.save();

        mPaint.setShader(null);

        mPaint.setColor(stokeColor);
        canvas.drawPath(mBgPath, mPaint);
        //
        mPaint.setColor(Color.WHITE);
        mPaint.setShader(leftGradient);
        canvas.drawPath(leftPath, mPaint);
        //
        mPaint.setShader(rightGradient);
        canvas.drawPath(rightPath, mPaint);
        //
        canvas.restore();
        isDrawing = false;
    }


    @Override
    public int getOpacity() {
        return OPAQUE;
    }

    @Override
    public void start() {
        if (animator != null && !animator.isRunning()) {
            animator.start();
        }
    }

    @Override
    public void stop() {
        if (animator != null && animator.isRunning()) {
            animator.end();
        }
    }

    @Override
    public boolean isRunning() {
        return animator != null && animator.isRunning();
    }
}

