package webbey.auth;

import org.apache.commons.lang3.StringUtils;
import webbey.auth.ejb.AuthentificationManager;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthBean implements Serializable{
    private boolean loggetIn;
    private String login;
    private String password;

    private String requestedPage;

    public String getRequestedPage() {
        return requestedPage;
    }

    public void setRequestedPage(String requestedPage) {
        this.requestedPage = requestedPage;
    }

    @EJB
    private AuthentificationManager authentificationManager;

    public void doLogin(){
        if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password)){
            loggetIn = false;
            return;
        }

        loggetIn = authentificationManager.loginAsUser(login,password);

        if (loggetIn){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(requestedPage);
                // redirect направляю на запрошеную старницу.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean isLoggetIn() {
        return loggetIn;
    }

    public void setLoggetIn(boolean loggetIn) {
        this.loggetIn = loggetIn;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
