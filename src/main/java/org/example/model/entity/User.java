package org.example.model.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer idUser;
    private String username;
    private String password;
    /**
     * Role of the sure.
     * Possible values:
     * 1 -> Administrator
     * 2 -> Medic
     * 3 -> Assistant
     */
    @NotNull
    private Integer rol;
}
