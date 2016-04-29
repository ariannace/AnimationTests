package it.reply.arian.skykidstests;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Arian Nace on 29/04/16.
 */
public class SkyKidsToolbar extends LinearLayout implements View.OnTouchListener{
    WavingView wave;
    TextView top;
    ImageView icon;
    private static final int LEFT_PADDING = 50;
    private static final int DELTA = 20;

    public SkyKidsToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        wave = new WavingView(context);
        LinearLayout items = new LinearLayout(context);
        items.setPadding(100,100,100,100);
        TextView item1 = new TextView(context);
        item1.setText("Item 1");
        item1.setPadding(20,20,20,20);
        item1.setTextSize(40);
        item1.setBackgroundColor(Color.RED);
        TextView item2 = new TextView(context);
        item2.setPadding(20,20,20,20);
        item2.setTextSize(40);
        item2.setText("Item 2");
        item2.setBackgroundColor(Color.CYAN);
        TextView item3 = new TextView(context);
        item3.setText("Item 3");
        item3.setTextSize(40);
        item3.setPadding(20,20,20,20);
        item3.setBackgroundColor(Color.GREEN);

        items.addView(item1);
        items.addView(item2);
        items.addView(item3);

        top = new TextView(context);
        top.setText("TOP");
        top.setPadding(LEFT_PADDING,0,0,0);

        top.setTextSize(40);
        top.setBackgroundColor(Color.GRAY);

        addView(top);
        addView(items);
        addView(wave);

        icon = new ImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.LEFT;
        params.leftMargin = 30;
        icon.setImageResource(R.mipmap.ic_launcher);
        icon.setOnTouchListener(this);
        addView(icon,params);
    }

    public SkyKidsToolbar(Context context) {
        this(context,null);
    }


    float lastPositionProcessed;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                lastPositionProcessed = event.getRawY();
                return true;
            case (MotionEvent.ACTION_MOVE) :
                float dPixels = (event.getRawY()- lastPositionProcessed);
                lastPositionProcessed = event.getRawY();
                wave.addHeight((int)dPixels);
                return true;
            default :
                return true;
        }
    }

    public void resetPosition(){
        top.setPadding(LEFT_PADDING,0,0,0);
        wave.setHeight(0);
    }

    public void followDelayedDrag(){
        steps = 0;
        handler.postDelayed(followDragStep,500);
    }

    AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();

    Handler handler = new Handler();
    private int steps;
    private static final int TOTAL_STEPS = 100;
    private Runnable followDragStep = new Runnable() {
        @Override
        public void run() {
            if(steps<TOTAL_STEPS){
                steps++;
                float interpolation = interpolator.getInterpolation(steps/TOTAL_STEPS);
                wave.setHeight((int)interpolation*WavingView.WAVE_MAX_HEIGHT);
                //top.setPadding(LEFT_PADDING,(int)((1-interpolation)*(WavingView.WAVE_MAX_HEIGHT)),0,0);
                handler.postDelayed(this,DELTA);
            }
        }
    };


}
