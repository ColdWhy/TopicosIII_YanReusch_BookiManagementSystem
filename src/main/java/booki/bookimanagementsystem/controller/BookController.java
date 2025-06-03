/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package booki.bookimanagementsystem.controller;

import booki.bookimanagementsystem.entity.BookEntity;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "bookController")
@SessionScoped
public class BookController implements Serializable {

    // Object representing a single book
    private BookEntity book = new BookEntity();

    // List representing all registered books
    private List<BookEntity> bookList = new ArrayList<>();

    public BookController() {
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public List<BookEntity> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookEntity> bookList) {
        this.bookList = bookList;
    }

}
