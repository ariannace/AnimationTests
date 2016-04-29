package it.reply.arian.skykidstests;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class HomeActivity extends AppCompatActivity{
    FrameLayout container;
    SkyKidsToolbar toolbar;
    int step;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        container = ((FrameLayout)findViewById(R.id.container));

        toolbar = new SkyKidsToolbar(this);
        container.addView(toolbar);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.resetPosition();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.followDelayedDrag();
            }
        });
    }


}
