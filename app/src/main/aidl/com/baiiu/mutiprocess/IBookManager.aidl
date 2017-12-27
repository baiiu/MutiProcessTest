// IBookManager.aidl
package com.baiiu.mutiprocess;
import com.baiiu.mutiprocess.Book;

interface IBookManager {

    List<Book> getBookList();

    void addBook(in Book book);


}
