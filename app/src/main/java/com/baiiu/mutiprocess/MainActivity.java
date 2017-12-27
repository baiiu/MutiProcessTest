package com.baiiu.mutiprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.baiiu.library.LogUtil;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.d(UserManager.INSTANCE.age + ", " + UserManager.sId);
        UserManager.sId += 1;
        UserManager.INSTANCE.age += 1;
        LogUtil.d(UserManager.INSTANCE.age + ", " + UserManager.sId);

        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager iBookManager = IBookManager.Stub.asInterface(service);
            try {
                iBookManager.addBook(new Book(4, "book4"));
                List<Book> bookList = iBookManager.getBookList();

                LogUtil.d(bookList);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override protected void onDestroy() {
        super.onDestroy();
        if (connection != null) {
            unbindService(connection);
        }
    }

    public void onClick(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
