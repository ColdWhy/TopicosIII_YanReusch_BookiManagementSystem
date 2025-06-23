package booki.bookimanagementsystem.controller;

import booki.bookimanagementsystem.entity.AuthorEntity;
import booki.bookimanagementsystem.facade.AuthorFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("authorController")
@SessionScoped
public class AuthorController implements Serializable {

    private String name;
    private Integer birth_year;
    private String nationality;

    @EJB
    private AuthorFacade authorFacade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    private List<AuthorEntity> authorList;

    @PostConstruct
    public void init() {
        authorList = authorFacade.findAll();
    }

    public List<AuthorEntity> getAuthorList() {
        return authorList;
    }

    public String saveAuthor() {
        AuthorEntity newAuthor = new AuthorEntity();
        newAuthor.setName(name);
        newAuthor.setBirth_year(birth_year);
        newAuthor.setNationality(nationality);

        authorFacade.create(newAuthor);

        name = null;
        birth_year = null;
        nationality = null;

        authorList = authorFacade.findAll();

        return "authorPage.xhtml?faces-redirect=true";
    }

    private AuthorEntity selectedAuthor = new AuthorEntity();

    public AuthorEntity getSelectedAuthor() {
        return selectedAuthor;
    }

    public void setSelectedAuthor(AuthorEntity selectedAuthor) {
        this.selectedAuthor = selectedAuthor;
    }

    public void loadAuthorById(Integer id) {
        selectedAuthor = authorFacade.find(id);
        if (selectedAuthor != null) {
            name = selectedAuthor.getName();
            birth_year = selectedAuthor.getBirth_year();
            nationality = selectedAuthor.getNationality();
        }
    }

    public String updateAuthor() {
        selectedAuthor.setName(name);
        selectedAuthor.setBirth_year(birth_year);
        selectedAuthor.setNationality(nationality);

        authorFacade.edit(selectedAuthor);
        authorList = authorFacade.findAll();
        return "authorPage.xhtml?faces-redirect=true";
    }

    public String deleteAuthor() {
        authorFacade.remove(selectedAuthor);
        authorList = authorFacade.findAll();
        return "authorPage.xhtml?faces-redirect=true";
    }

}
