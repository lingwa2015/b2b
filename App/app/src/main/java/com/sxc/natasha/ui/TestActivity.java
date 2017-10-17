package com.sxc.natasha.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class TestActivity extends Activity {
    private Button btn = null;
    private ImageView image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        image = (ImageView) findViewById(R.id.image);
        btn = (Button) findViewById(R.id.btn);
        final Animation animation = AnimationUtils.loadAnimation(TestActivity.this,R.anim.addbasket_animator);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this,"click",Toast.LENGTH_SHORT).show();
                image.setVisibility(View.VISIBLE);
                image.startAnimation(animation);
                image.setVisibility(View.GONE);
            }
        });
    }
}
