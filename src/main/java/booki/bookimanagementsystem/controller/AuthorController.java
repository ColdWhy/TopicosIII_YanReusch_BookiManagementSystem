/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package booki.bookimanagementsystem.controller;

import booki.bookimanagementsystem.entity.AuthorEntity;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "authorController")
@SessionScoped
public class AuthorController implements Serializable {

    // Represents a single author to be added
    private AuthorEntity author = new AuthorEntity();

    // Represents the list of all authors
    private List<AuthorEntity> authorList = new ArrayList<>();

    public AuthorController() {
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public List<AuthorEntity> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<AuthorEntity> authorList) {
        this.authorList = authorList;
    }
}
