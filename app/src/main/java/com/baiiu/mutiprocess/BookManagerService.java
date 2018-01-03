package com.baiiu.mutiprocess;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.baiiu.library.LogUtil;
import com.baiiu.mutiprocess.custom.BookManagerImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * author: baiiu
 * date: on 17/12/27 17:15
 * description:
 */
public class BookManagerService extends Service {
    private final List<Book> list = new ArrayList<>();

    private Binder mBinder = new BookManagerImpl() {

        @Override public List<Book> getBookList() throws RemoteException {
            LogUtil.d(Binder.getCallingPid() + "," + Process.myPid());
            synchronized (list) {
                return list;
            }
        }

        @Override public void addBook(Book book) throws RemoteException {
            synchronized (list) {
                list.add(book);
            }
        }
    };


    @Override public void onCreate() {
        super.onCreate();
        list.add(new Book(1, "book1"));
        list.add(new Book(2, "book2"));
        list.add(new Book(3, "book3"));
    }


    @Nullable @Override public IBinder onBind(Intent intent) {
        return mBinder;
    }

}
