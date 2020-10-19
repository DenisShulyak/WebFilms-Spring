package com.example.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails { //UserDetails предоставляет необходимую информацию
    // для построения объекта Authentication
    // из DAO объектов приложения или других источников данных системы безопасности.
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) // fetch определяет как будут загружаться данные о нашей сущности
    // есть два вида подгрузки жадный и ленивый разница в том что жадный сразу подгружает все роли, ленивый подгружает когда обращаешься
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id")) // говорит нам, что роли хранятся в отдельной таблице,
    // соедиенение будет через юзер айди
    @Enumerated(EnumType.STRING) // enum будем хранить в виде строки

    private Set<Role> roles; // set нужен нам для хранения ролей юзер админ гость и т.д.

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public void setUsername( String username ) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive( boolean active ) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles( Set<Role> roles ) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return  username ;
    }
}
