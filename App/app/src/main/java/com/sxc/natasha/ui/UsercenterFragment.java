package com.sxc.natasha.ui;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.ui.login.Login;

/**
 * Description: UsercenterFragment 用户中心Fragment
 *
 * @author:     zhengshutian
 * @version:    1.0
 * Filename:    UserController.java
 * Create at:   2015-03-15
 *
 * Copyright:   Copyright (c)2015
 * Company:     songxiaocai
 *
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-03-15      zhengshutian    1.0         1.0 Version
 */
public class UsercenterFragment extends Fragment {

    private View usercenterView;
    private RelativeLayout myInfo = null;
    private RelativeLayout pickhouse = null;
    private RelativeLayout resetPwd = null;
    private Button exitBtn= null;

    private RelativeLayout contact = null;

    private RelativeLayout checkUpdate = null;

    private TextView name = null;
    private TextView contactPhone = null;

    LayoutInflater inflater =null;
    private int currentVersionCode;
    private int lastVersionCode;


    /**
     * 页面渲染
     *
     * @author zhengshutian
     *
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        usercenterView=inflater.inflate(R.layout.usercenter_fragment, null);

        findView();
        setClickListener();

        return usercenterView;
    }

    /**
     * 绑定view
     *
     * @author zhengshutian
     *
     */
    private void findView(){

        inflater=(LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //个人信息
        myInfo = (RelativeLayout) usercenterView.findViewById(R.id.myInfo_layout);

        //提货信息管理
        pickhouse =(RelativeLayout)usercenterView.findViewById(R.id.pickhouse_layout);

        //修改密码
        resetPwd = (RelativeLayout) usercenterView.findViewById(R.id.resetPwd_layout);

        //联系客服
        contact = (RelativeLayout) usercenterView.findViewById(R.id.contact_layout);

        //检查更新
        checkUpdate = (RelativeLayout) usercenterView.findViewById(R.id.checkUpdate_layout);

        //注销
        exitBtn = (Button)usercenterView.findViewById(R.id.exit_button);

        name = (TextView) usercenterView.findViewById(R.id.usercenter_name);
        name.setText(UserCache.getUser(usercenterView.getContext()).getUserName());

        contactPhone = (TextView) usercenterView.findViewById(R.id.contactPhone);
        contactPhone.setText("400-888-888");

        getLastVersionCode();
    }

    /**
     * 绑定Listener
     *
     * @author zhengshutian
     *
     */
    private void setClickListener(){

        //个人信息
        myInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MyInfoActivity.class);
                intent.putExtra("page","个人信息");
                getActivity().startActivity(intent);
            }
        });

        pickhouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PickupInfoActivity.class);
                intent.putExtra("page","提货信息管理");
                intent.putExtra("fromPage", "userCenter");
                getActivity().startActivity(intent);
            }
        });

        resetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ResetPwdActivity.class);
                intent.putExtra("page","修改密码");
                getActivity().startActivity(intent);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = inflater.inflate(R.layout.home_recharge_layout, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(view);
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("拨打", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:15874980315"));
                        startActivity(intent);
                    }
                });
                builder.show();


            }
        });

        checkUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), Main.class);
                //getActivity().startActivity(intent);
                PackageManager manager = getActivity().getPackageManager();
                try {
                    PackageInfo info = manager.getPackageInfo(getActivity().getPackageName(), 0);
                    String appVersion = info.versionName; // 版本名
                    currentVersionCode = info.versionCode; // 版本号
                    System.out.println(currentVersionCode + " " + appVersion);
                } catch (PackageManager.NameNotFoundException e) {
                    // TODO Auto-generated catch blockd
                    e.printStackTrace();
                }

                if(currentVersionCode<lastVersionCode){
                    showUpdateDialog();
                }
                else{
                    Toast.makeText(getActivity(), "已经是最新版本", Toast.LENGTH_SHORT).show();
                }

            }
        });


        exitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserCache.saveUser("",usercenterView.getContext());
                Intent intent = new Intent(getActivity(), Login.class);
                getActivity().startActivity(intent);
            }
        });

    }

    /**
     * 更新对话框
     *
     * @author zhengshutian
     *
     */
    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("检测到新版本");
        builder.setMessage("是否下载更新?");
        builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), NotificationUpdateActivity.class);
                intent.putExtra("page","正在下载...");
                startActivity(intent);
//				MapApp.isDownload = true;
                //app.setDownload(true);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();

            }
        });
        builder.show();
    }

    /**
     * 获取服务器上最新app版本编号
     *
     * @author zhengshutian
     *
     */
    private void getLastVersionCode(){

        AbHttpUtil httpUtil= AbHttpUtil.getInstance(getActivity());

        httpUtil.get(SXC_API.GET_LAST_VERSION_CODE, new AbStringHttpResponseListener() {

            @Override
            public void onSuccess(int statusCode, String content) {
                Log.d("UpdateNameActivity", "onSuccess");

                lastVersionCode = Integer.valueOf(content).intValue();

                //Toast.makeText(UpdateNameActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            };

            @Override
            public void onStart() {
                Log.d("getLastVersionCode", "onStart");
            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                Log.d("getLastVersionCode", "onFailure");
                Log.d("getLastVersionCode", statusCode+"");
                Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Log.d("getLastVersionCode", "onFinish");
            };

        });


    }
}
