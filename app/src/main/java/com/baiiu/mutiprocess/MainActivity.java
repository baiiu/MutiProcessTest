package com.baiiu.mutiprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.baiiu.library.LogUtil;
import com.baiiu.mutiprocess.custom.BookManagerImpl;
import com.baiiu.mutiprocess.custom.IBookManager;
import java.util.List;

import static com.baiiu.mutiprocess.BookManagerService.TAG_BINDER;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.d(UserManager.INSTANCE.age + ", " + UserManager.sId);
        UserManager.sId += 1;
        UserManager.INSTANCE.age += 1;
        LogUtil.d(UserManager.INSTANCE.age + ", " + UserManager.sId);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.d("onServiceConnected:" + name); // BinderProxy，只有这个才有跨进程的能力
            LogUtil.d(TAG_BINDER, service);

            IBookManager iBookManager = BookManagerImpl.asInterface(service);
            try {
                iBookManager.addBook(new Book(4, "book4"));
                LogUtil.d(TAG_BINDER, Process.myPid());
                List<Book> bookList = iBookManager.getBookList();


                LogUtil.d(bookList);

            } catch (RemoteException e) {
                LogUtil.e(e.toString());
            }
        }

        @Override public void onServiceDisconnected(ComponentName name) {
            LogUtil.d("onServiceDisconnected:" + name);
        }
    };

    @Override protected void onDestroy() {
        super.onDestroy();
        if (connection != null) {
            unbindService(connection);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_bind:
                Intent intent = new Intent(this, BookManagerService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.tv_second:
                startActivity(new Intent(this, SecondActivity.class));
                break;
        }
    }
}
