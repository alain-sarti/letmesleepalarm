package de.technophilia.letmesleepalarm.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by alainsarti on 07/04/2017.
 */

public class DraggableCircle extends View implements View.OnTouchListener {
    static int x, y, r = 255, g = 0, b = 0;
    final static int radius = 150;
    Paint paint;

    public DraggableCircle(Context context) {
        super(context);
        init();
    }

    public DraggableCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DraggableCircle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setARGB(255, r, g, b);

        setFocusable(true);

        this.setOnTouchListener(this);
    }

    public void onDraw(Canvas canvas) {
        paint.setARGB(255, r, g, b);

        //drawing the circle
        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        x = (int) event.getX() - (radius / 2);      //logic to plot the circle in exact touch place
        y = (int) event.getY() - (radius / 2);
        invalidate();

        if (event.getAction() == MotionEvent.ACTION_UP) {
            paint.setARGB(12, r, g, b);
        }

        return true;
    }
}
