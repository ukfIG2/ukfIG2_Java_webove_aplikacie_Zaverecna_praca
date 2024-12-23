package sk.ukf.Zaverecna_praca.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sk.ukf.Zaverecna_praca.Entity.User;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // Override methods from UserDetails interface

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert Role enum to SimpleGrantedAuthority
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // Additional custom methods

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getInitials() {
        String firstNameInitial = user.getFirstName().substring(0, 1).toUpperCase();
        String lastNameInitial = user.getLastName().substring(0, 1).toUpperCase();
        return firstNameInitial + lastNameInitial;
    }

    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    // Account status methods
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
}
