package org.example.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medic {
    @Id
    @GeneratedValue
    private Integer idMedic;
    private String nume;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User idUser;
}
