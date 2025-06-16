package booki.bookimanagementsystem.controller;

import booki.bookimanagementsystem.entity.AuthorEntity;
import booki.bookimanagementsystem.entity.BookEntity;
import booki.bookimanagementsystem.facade.AuthorFacade;
import booki.bookimanagementsystem.facade.BookFacade;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;

@Named(value = "bookController")
@SessionScoped
public class BookController implements Serializable {

    @EJB
    private BookFacade bookFacade;

    @EJB
    private AuthorFacade authorFacade;

    private BookEntity book;

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public List<BookEntity> getBookList() {
        return bookFacade.fetchAll();
    }

    public List<AuthorEntity> getAuthorList() {
        return authorFacade.fetchAll();
    }

    private int generateId() {
        int id = 1;
        if (!getBookList().isEmpty()) {
            id = getBookList().size() + 1;
        }
        return id;
    }

    public void prepareCreate() {
        book = new BookEntity();
    }

    public void addBook() {
        try {
            book.setId(generateId());
            getBookList().add(book);
            addSuccessMessage("Book successfully created!");
            book = new BookEntity();
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }
    }

    public void create() {
        generateId();
        try {
            bookFacade.createReturn(book);
            addSuccessMessage("Book successfully created!");
            // book = new BookEntity();
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }
    }

    public void update() {
        try {
            bookFacade.edit(book);
            addSuccessMessage("Book successfully updated!");
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }
    }

    public void delete() {
        try {
            bookFacade.remove(book);
            book = null;
            addSuccessMessage("Book successfully deleted!");
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage faceMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, faceMsg);
    }

    public static void addErrorMessage(String msg) {
        FacesMessage faceMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, faceMsg);
    }
}
