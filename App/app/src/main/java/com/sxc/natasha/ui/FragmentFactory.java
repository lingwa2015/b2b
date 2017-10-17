package com.sxc.natasha.ui;

import android.app.Fragment;
import android.widget.TextView;

/**
 * @author longpo
 */
public class FragmentFactory {

    private static HomeFragment homeFragment;
    private static BasketFragment basketFragment;
    private static MyorderFragment myorderFragment;




    public static Fragment getInstanceByIndex(int index, TextView title) {
        Fragment fragment = null;
        switch (index) {
            case 1:
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                }
                title.setText("首页");
                return homeFragment;
            case 2:
                if(basketFragment == null){
                    basketFragment = new BasketFragment();
                }
                title.setText("菜篮子");
                return basketFragment;
            case 3:
                if(myorderFragment == null){
                    myorderFragment  = new MyorderFragment();
                }
                title.setText("我的订单");
                return myorderFragment;
            case 4:
                fragment = new WalletFragment();
                title.setText("我的钱包");
                break;
            case 5:
                fragment = new UsercenterFragment();
                title.setText("个人中心");
                break;
        }
        return fragment;
    }
}
