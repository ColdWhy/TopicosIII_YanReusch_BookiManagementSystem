package booki.bookimanagementsystem.controller;

import booki.bookimanagementsystem.entity.UserEntity;
import booki.bookimanagementsystem.facade.UserFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    private String email;
    private String password;
    private UserEntity user = new UserEntity();

    @EJB
    private UserFacade userFacade;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String login() {
        try {
            UserEntity userEntity = userFacade.findByEmailAndPassword(email, password);
            if (userEntity != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", userEntity);
                return "index_users.xhtml?faces-redirect=true";
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", "Invalid email or password");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public String register() {
        try {
            userFacade.createReturn(user);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User registered successfully!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            user = new UserEntity(); // reset form
            return "userLogin.xhtml?faces-redirect=true";
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
