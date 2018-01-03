package com.baiiu.mutiprocess.custom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.baiiu.mutiprocess.Book;
import java.util.List;

/**
 * author: baiiu
 * date: on 18/1/3 19:15
 * description:
 */
public abstract class BookManagerImpl extends Binder implements IBookManager {

    protected BookManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    @Override public IBinder asBinder() {
        return this;
    }

    public static IBookManager asInterface(android.os.IBinder obj) {
        if (obj == null) return null;

        IInterface inn = obj.queryLocalInterface(DESCRIPTOR);
        if (inn != null && inn instanceof IBookManager) {
            return (IBookManager) inn;
        }

        return new BookManagerImpl.Proxy(obj);
    }

    @Override protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getBookList: {
                data.enforceInterface(DESCRIPTOR);
                java.util.List<Book> _result = this.getBookList();
                reply.writeNoException();
                reply.writeTypedList(_result);
                return true;
            }
            case TRANSACTION_addBook: {
                data.enforceInterface(DESCRIPTOR);
                Book _arg0;
                if ((0 != data.readInt())) {
                    _arg0 = Book.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                this.addBook(_arg0);
                reply.writeNoException();
                return true;
            }
        }

        return super.onTransact(code, data, reply, flags);
    }

    private static class Proxy implements IBookManager {
        private IBinder mRemote;

        Proxy(IBinder remote) {
            this.mRemote = remote;
        }

        @Override public IBinder asBinder() {
            return mRemote;
        }

        public String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }


        @Override public List<Book> getBookList() throws RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            java.util.List<com.baiiu.mutiprocess.Book> _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(TRANSACTION_getBookList, _data, _reply, 0);
                _reply.readException();
                _result = _reply.createTypedArrayList(com.baiiu.mutiprocess.Book.CREATOR);
            } finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }

        @Override public void addBook(Book book) throws RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                if ((book != null)) {
                    _data.writeInt(1);
                    book.writeToParcel(_data, 0);
                } else {
                    _data.writeInt(0);
                }
                mRemote.transact(TRANSACTION_addBook, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }

    }

}
