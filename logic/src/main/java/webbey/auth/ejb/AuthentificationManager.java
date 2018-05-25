package webbey.auth.ejb;

import org.apache.commons.lang3.StringUtils;
import webbey.auth.domain.Admin;
import webbey.auth.domain.Credentials;
import webbey.auth.domain.ShopUser;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class AuthentificationManager {
    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public boolean loginAsUser(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) return false; //проверка на null и на пустоту

        Credentials credentials = entityManager.find(Credentials.class, email);
        if (credentials == null) return false;

       if (!password.equals(credentials.getPassword())) return false;

        ShopUser shopUser = credentials.getShopUser();
        if (shopUser == null)return false;

        return true;
    }
    public boolean loginAsAdmin(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) return false; //проверка на null и на пустоту

        Credentials credentials = entityManager.find(Credentials.class, email);
        if (credentials == null) return false;

        if (!password.equals(credentials.getPassword())) return false;

        Admin admin = credentials.getAdmin();
        if (admin == null)return false;

        return true;
    }

}
