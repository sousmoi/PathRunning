package wholam.path.drawable;

import android.graphics.Path;
import android.support.annotation.ColorInt;

/**
 * created by sfx on 2018/5/2.
 */

public class DefenseSpreadDrawable extends AbsSpreadDrawable {
    public DefenseSpreadDrawable(int stroke, @ColorInt int startColor, @ColorInt int endColor) {
        super(stroke, startColor, endColor);
    }

    @Override
    protected void drawPath(Path bgPath, int left, int top, int right, int bottom, int stroke) {
        final int width = right - left;
        final int height = bottom - top;
        SvgToPath svg = new SvgToPath(bgPath, 128, 128, width, height, left, top);
        svg.moveTo(55.65f, 7.84f);
        svg.cubicTo(66.71f, 5.97f, 77.91f, 8.38f, 88.58f, 11.26f);
        svg.cubicTo(96.06f, 13.51f, 103.44f, 16.13f, 110.55f, 19.35f);
        svg.cubicTo(112.79f, 20.23f, 112.63f, 22.97f, 112.69f, 24.95f);
        svg.cubicTo(112.64f, 38.97f, 113.59f, 53.04f, 112.29f, 67.04f);
        svg.cubicTo(111.83f, 76.71f, 109.46f, 86.63f, 103.67f, 94.57f);
        svg.cubicTo(96.74f, 104.15f, 86.63f, 110.74f, 76.65f, 116.78f);
        svg.cubicTo(72.59f, 119.05f, 68.62f, 121.86f, 63.93f, 122.57f);
        svg.cubicTo(59.53f, 121.93f, 55.80f, 119.30f, 52.00f, 117.18f);
        svg.cubicTo(40.59f, 110.35f, 28.76f, 102.79f, 21.89f, 91.02f);
        svg.cubicTo(16.21f, 81.37f, 15.60f, 69.89f, 15.00f, 58.99f);
        svg.cubicTo(14.98f, 47.30f, 14.96f, 35.60f, 15.17f, 23.91f);
        svg.cubicTo(15.09f, 21.91f, 15.89f, 19.66f, 18.00f, 19.06f);
        svg.cubicTo(30.09f, 14.00f, 42.60f, 9.53f, 55.65f, 7.84f);
    }
}
