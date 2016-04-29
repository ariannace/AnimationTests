package it.reply.arian.skykidstests;

/**
 * Created by Arian Nace on 28/04/16.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class WavingView extends View {
    public static final int WAVE_MAX_HEIGHT = 300;
    private final Paint coloredPaint, transparentPaint;
    private int halfWidth, halfHeight;
    private Path path,invertedPath;
    private boolean isFlippedVertically;

    {
        transparentPaint = new Paint();
        transparentPaint.setColor(Color.TRANSPARENT);
        coloredPaint = new Paint();
        coloredPaint.setAntiAlias(true);
        coloredPaint.setColor(Color.BLUE);
        coloredPaint.setStrokeWidth(5);
        coloredPaint.setStyle(Paint.Style.FILL);
    }

    public WavingView(Context context) {
        super(context);
    }

    public WavingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WavingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private ViewGroup.LayoutParams params;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        halfWidth = w / 2;
        halfHeight = h / 2;
        path = new Path();
        path.lineTo(0, 2 * halfHeight);
        path.quadTo(halfWidth / 2, 2 * halfHeight, halfWidth, halfHeight);
        path.quadTo(3 * halfWidth / 2, 0, 2 * halfWidth, 0);
        path.close();

        invertedPath = new Path();
        invertedPath.lineTo(2*halfWidth, 0);
        invertedPath.lineTo(2*halfWidth, 2*halfHeight);
        invertedPath.quadTo(3*halfWidth/2, 2*halfHeight, halfWidth, halfHeight);
        invertedPath.quadTo(halfWidth / 2, 0, 0,0);
        invertedPath.close();
        params = getLayoutParams();
    }

    public void setHeight(int h){
        params.height = h;
        requestLayout();
    }

    public void addHeight(int d) {
        params.height += d;
        if (params.height < 0) {
            params.height = 0;
        }
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas c) {
        c.drawPaint(transparentPaint);
        if(isFlippedVertically){
            c.drawPath(invertedPath, coloredPaint);
        }else{
            c.drawPath(path, coloredPaint);
        }
    }
}
