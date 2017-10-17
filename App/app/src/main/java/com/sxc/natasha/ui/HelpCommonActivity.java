package com.sxc.natasha.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.sxc.natasha.ui.R;

import org.w3c.dom.Text;

public class HelpCommonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.help_common_layout);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);

        TextView title = (TextView) findViewById(R.id.title);
        TextView title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);
        String page = getIntent().getStringExtra("page");

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if ("help".equals(page)) {
            title.setText("使用帮助");
            String url = "http://www.bieleba.com/sxcv2/usehelp.html#address=1&CSUM=1";
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            Fragment fragment = new WebFragment();
            Bundle params = new Bundle();
            params.putString("url", url);
            fragment.setArguments(params);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        } else {
            title.setText("服务承诺");
            RelativeLayout content = (RelativeLayout) findViewById(R.id.content);
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.u7);
            content.addView(iv);
        }
    }
}
