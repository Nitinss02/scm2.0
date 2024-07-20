package com.scm.scm20.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user")
public class user implements UserDetails {

    @Id
    private String userId;
    @Column(nullable = false, name = "UserName")
    private String name;

    @Column(unique = true)
    private String email;
    @Getter(value = AccessLevel.NONE)
    private String password;
    private String about;
    private String profilepic;
    private String mobileNumber;
    private boolean enable = false;
    private boolean varifiedEmail = false;
    private boolean varifiedMobile = false;

    @Enumerated(value = EnumType.STRING)
    private porviders provider = porviders.SELF;
    private String providerId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<contact> contact = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> role = new ArrayList<>();

    private String emailToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // List of roles(ADMIN, USER)
        // Collection of simpleGrantedAuthority(role(ADMIN,USER))
        List<SimpleGrantedAuthority> roles = role.stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return this.enable;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

}
