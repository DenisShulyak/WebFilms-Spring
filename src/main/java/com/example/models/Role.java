package com.example.models;

import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority { //Кроме отдельных примитивных типов данных и классов в Java есть такой тип как enum или перечисление.
    // Перечисления представляют набор логически связанных констант.
    //GrantedAuthority отражает разрешения выданные пользователю в масштабе всего приложения,
    // такие разрешения (как правило называются «роли»), например ROLE_ANONYMOUS, ROLE_USER, ROLE_ADMIN.
    USER,
    ADMIN,
    GUEST;
    @Override
    public String getAuthority() {
        return name();
    }

}
