package controller;

import model.User;
import service.IUser;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UserBean {

    @EJB
    private IUser userEJB;

    private User user;

    public UserBean(){

    }

    @PostConstruct
    public void init(){
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String logon(){
        User u = userEJB.findUser(user.getUsername(), user.getPassword());
        if(u!= null){
            user = u;
            return "accueil?faces-redirect=true";
        }
        FacesMessage msg = new FacesMessage("Login ou password incorrect !",
                "Login ou password incorrect !");
        FacesContext.getCurrentInstance().addMessage("info",msg);
        return "login";
    }
}
