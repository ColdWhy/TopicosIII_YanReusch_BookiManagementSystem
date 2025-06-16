package booki.bookimanagementsystem.controller;

import booki.bookimanagementsystem.entity.LibrarianEntity;
import booki.bookimanagementsystem.facade.LibrarianFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("librarianController")
@SessionScoped
public class LibrarianController implements Serializable {

    private String name;
    private String password;

    @EJB
    private LibrarianFacade librarianFacade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        try {
            LibrarianEntity librarian = librarianFacade.findByNameAndPassword(name, password);
            if (librarian != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("librarian", librarian);
                return "index.xhtml?faces-redirect=true";
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", "Invalid username or password");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }
}
