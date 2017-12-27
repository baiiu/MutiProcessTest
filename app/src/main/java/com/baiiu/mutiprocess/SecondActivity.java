package com.baiiu.mutiprocess;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.baiiu.library.LogUtil;

/**
 * author: baiiu
 * date: on 17/12/27 15:18
 * description:
 */
public class SecondActivity extends AppCompatActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        LogUtil.d(UserManager.INSTANCE.age + ", " + UserManager.sId);
    }

    public void onClick(View view) {
        startActivity(new Intent(this, ThirdActivity.class));
    }
}
