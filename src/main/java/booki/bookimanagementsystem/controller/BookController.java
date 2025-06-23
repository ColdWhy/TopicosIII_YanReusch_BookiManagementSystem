package booki.bookimanagementsystem.controller;

import booki.bookimanagementsystem.entity.BookEntity;
import booki.bookimanagementsystem.entity.UserEntity;
import booki.bookimanagementsystem.facade.BookFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named("bookController")
@SessionScoped
public class BookController implements Serializable {

    private String title;
    private Integer release_year;
    private String genre;
    private Date due_date;
    private String author;
    private String language;

    @Inject
    private BookFacade bookFacade;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    private List<BookEntity> bookList;

@PostConstruct
public void init() {
    bookList = bookFacade.findAll();
    filteredBookList = bookList;
}

    public List<BookEntity> getBookList() {
        return bookList;
    }

    public String getStatusLabel(BookEntity book) {
        if (book.getDue_date() == null) {
            return "Available";
        } else {
            Date today = new Date();
            if (book.getDue_date().before(today)) {
                return "Borrowed";
            } else {
                return "Borrowed";
            }
        }
    }

    public String getStatusStyle(BookEntity book) {
        if (book.getDue_date() == null) {
            return "status-available";
        } else {
            Date today = new Date();
            if (book.getDue_date().before(today)) {
                return "status-overdue";
            } else {
                return "status-borrowed";
            }
        }
    }

    public String saveBook() {
        BookEntity newBook = new BookEntity();
        newBook.setTitle(title);
        newBook.setGenre(genre);
        newBook.setAuthor(author);
        newBook.setRelease_year(release_year);
        newBook.setLanguage(language);
        newBook.setDue_date(null);

        bookFacade.create(newBook);

        title = null;
        genre = null;
        author = null;
        release_year = null;
        language = null;

        bookList = bookFacade.findAll();

        return "index.xhtml?faces-redirect=true";
    }

    private BookEntity selectedBook = new BookEntity();

    public BookEntity getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(BookEntity selectedBook) {
        this.selectedBook = selectedBook;
    }

    public void loadBookById(Integer id) {
        selectedBook = bookFacade.find(id);
        if (selectedBook != null) {
            title = selectedBook.getTitle();
            genre = selectedBook.getGenre();
            author = selectedBook.getAuthor();
            language = selectedBook.getLanguage();
            release_year = selectedBook.getRelease_year();
            due_date = selectedBook.getDue_date();
        }
    }

    public String updateBook() {
        selectedBook.setTitle(title);
        selectedBook.setGenre(genre);
        selectedBook.setAuthor(author);
        selectedBook.setLanguage(language);
        selectedBook.setRelease_year(release_year);
        selectedBook.setDue_date(due_date);

        bookFacade.edit(selectedBook);
        filteredBookList = bookFacade.findAll();
        return "index.xhtml?faces-redirect=true";
    }

    public String deleteBook() {
        bookFacade.remove(selectedBook);
        filteredBookList = bookFacade.findAll();
        return "index.xhtml?faces-redirect=true";
    }

    private String searchTitle = "";
    private List<BookEntity> filteredBookList;

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public List<BookEntity> getFilteredBookList() {
        if (filteredBookList == null) {
            return bookList;
        }
        return filteredBookList;
    }

    public void filterBooksByTitle() {
        if (searchTitle == null || searchTitle.trim().isEmpty()) {
            filteredBookList = bookList;
        } else {
            String lowerCaseSearch = searchTitle.toLowerCase();
            filteredBookList = bookList.stream()
                    .filter(book -> book.getTitle() != null && book.getTitle().toLowerCase().contains(lowerCaseSearch))
                    .toList();
        }
    }

}
