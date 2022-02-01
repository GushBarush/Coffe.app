package com.example.coffeapp.entity.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "last_number", nullable = false)
    private String userNumber;

    @Column(name = "coffee", nullable = false)
    private int coffee;

    @Column(name = "coffee_happy", nullable = false)
    private int happyCoffee;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Override
    public String getUsername() {
        return phoneNumber;
    }

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
        boolean result = false;

        for (Role role : roles) {
            if (role.getRoleName().equals("ADMIN")) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean isBarista() {
        boolean result = false;

        for (Role role : roles) {
            if (role.getRoleName().equals("BARISTA")) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", coffee=" + coffee +
                ", happyCoffee=" + happyCoffee +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}
