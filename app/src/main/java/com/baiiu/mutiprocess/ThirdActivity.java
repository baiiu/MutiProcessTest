package com.baiiu.mutiprocess;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.baiiu.library.LogUtil;

/**
 * author: baiiu
 * date: on 17/12/27 15:19
 * description:
 */
public class ThirdActivity extends AppCompatActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        LogUtil.d(UserManager.INSTANCE.age + ", " + UserManager.sId);
    }

}
