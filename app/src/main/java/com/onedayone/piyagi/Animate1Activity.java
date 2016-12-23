package com.onedayone.piyagi;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Animate1Activity extends AppCompatActivity {

    private AnimationDrawable frameAnimation;
    private ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (ImageView) findViewById(R.id.imageAnimation);

        view.setBackgroundResource(R.drawable.test);

        frameAnimation = (AnimationDrawable) view.getBackground();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            frameAnimation.start();
        } else {
            frameAnimation.stop();
        }
    }
}
