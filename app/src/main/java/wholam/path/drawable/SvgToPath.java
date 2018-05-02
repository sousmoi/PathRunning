package wholam.path.drawable;


import android.graphics.Path;
import android.graphics.RectF;

/**
 * created by sfx on 2018/4/20.
 */

public class SvgToPath {
    private final Path path;
    private final float dx;
    private final float dy;
    private final float factorX;
    private final float factorY;

    public SvgToPath(Path path, float srcWidth, float srcHeight, float tagWidth, float tagHeight, float factorX, float factorY) {
        this.path = path;
        this.factorX = factorX;
        this.factorY = factorY;
        this.dx = tagWidth / srcWidth;
        this.dy = tagHeight / srcHeight;
    }

    public final void moveTo(float x, float y) {
        path.moveTo(factorX + x * dx, factorY + y * dy);
    }

    public final void cubicTo(float x1, float y1
            , float x2, float y2
            , float x3, float y3) {
        path.cubicTo(factorX + x1 * dx, factorY + y1 * dy
                , factorX + x2 * dx, factorY + y2 * dy
                , factorX + x3 * dx, factorY + y3 * dy);
    }

}
