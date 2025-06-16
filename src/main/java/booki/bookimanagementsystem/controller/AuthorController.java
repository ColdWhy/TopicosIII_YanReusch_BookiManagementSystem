package booki.bookimanagementsystem.controller;

import booki.bookimanagementsystem.entity.AuthorEntity;
import booki.bookimanagementsystem.facade.AuthorFacade;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;

@Named(value = "authorController")
@SessionScoped
public class AuthorController implements Serializable {

    @EJB
    private AuthorFacade ejbFacade;

    private AuthorEntity selectedAuthor;

    public AuthorEntity getSelectedAuthor() {
        return selectedAuthor;
    }

    public void setSelectedAuthor(AuthorEntity selectedAuthor) {
        this.selectedAuthor = selectedAuthor;
    }

    public List<AuthorEntity> getAuthorList() {
        return ejbFacade.fetchAll();
    }

    private int generateId() {
        int id = 1;
        if (!getAuthorList().isEmpty()) {
            id = getAuthorList().size() + 1;
        }
        return id;
    }

    public void prepareCreate() {
        selectedAuthor = new AuthorEntity();
    }

    private void showMessage() {
        String msg = "Author added: " + selectedAuthor.getName();
        FacesMessage fm = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(msg, fm);
    }

    public void addAuthor() {
        try {
            selectedAuthor.setId(generateId());
            getAuthorList().add(selectedAuthor);
            addSuccessMessage("Author successfully created!");
            selectedAuthor = new AuthorEntity();
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }
    }

    public void create() {
        generateId();
        try {
            ejbFacade.createReturn(selectedAuthor);
            addSuccessMessage("Author successfully created!");
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }
    }

    public void update() {
        try {
            ejbFacade.edit(selectedAuthor);
            addSuccessMessage("Author successfully updated!");
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }
    }

    public void delete() {
        try {
            ejbFacade.remove(selectedAuthor);
            selectedAuthor = null;
            addSuccessMessage("Author successfully deleted!");
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
