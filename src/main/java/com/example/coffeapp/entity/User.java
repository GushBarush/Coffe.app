package com.example.coffeapp.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String nameUser;
    private String userNumber;
    @Value("0")
    private Long coffee;
    @Value("0")
    private Long happyCoffee;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
        return isActive();
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public boolean isBarista() {
        return roles.contains(Role.BARISTA);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String Uname) {
        this.nameUser = Uname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getCoffee() {
        return coffee;
    }

    public void setCoffee(Long coffee) {
        this.coffee = coffee;
    }

    public Long getHappyCoffee() {
        return happyCoffee;
    }

    public void setHappyCoffee(Long happyCoffee) {
        this.happyCoffee = happyCoffee;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public void setNewUserNumber() {
        this.userNumber = username.substring(username.length() - 4);
    }

    public void addCoffee() {
        if (coffee == 5) {
            coffee = (long) 0;
            happyCoffee ++;
        } else
            coffee ++;
    }

    public void delHappyCoffee() {
        if (happyCoffee == (long) 0) {

        } else
            happyCoffee --;
    }
}
