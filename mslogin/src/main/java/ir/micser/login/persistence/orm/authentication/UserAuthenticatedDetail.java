package ir.micser.login.persistence.orm.authentication;

import com.motaharinia.msutility.customexception.UtilityException;
import ir.micser.login.persistence.orm.adminuser.AdminUser;
import ir.micser.login.presentation.authentication.UserAuthenticatedModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserAuthenticatedDetail implements UserDetails {

    //    private Integer id;
    private UserAuthenticatedModel userAuthenticatedModel = new UserAuthenticatedModel();

    public UserAuthenticatedDetail(AdminUser userAuthenticated) throws UtilityException {
        this.setUserAuthenticatedModel(UserAuthenticatedModel.getUserAuthenticatedModel(userAuthenticated));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.getUserAuthenticatedModel().getPassword();
    }

    @Override
    public String getUsername() {
        return this.getUserAuthenticatedModel().getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public UserAuthenticatedModel getUserAuthenticatedModel() {
        return userAuthenticatedModel;
    }

    public void setUserAuthenticatedModel(UserAuthenticatedModel userAuthenticatedModel) {
        this.userAuthenticatedModel = userAuthenticatedModel;
    }

}
