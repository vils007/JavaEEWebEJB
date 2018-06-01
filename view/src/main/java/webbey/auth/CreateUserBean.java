package webbey.auth;

import webbey.auth.ejb.AuthentificationManager;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CreateUserBean implements Serializable {
    private String email;
    private String password;
    private String name;

    @EJB
    private AuthentificationManager authentificationManager;

    public void addUser(){
        authentificationManager.addUser(email,password,name);
    }


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
