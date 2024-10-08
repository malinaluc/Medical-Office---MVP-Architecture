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
public class FisaMedicala {

    @Id
    @GeneratedValue
    private Long idFisaMedicala;
    private String diagnostic;
    private String simptome;
    private String tratament;
    private Integer varstaPacient;

    @ManyToOne
    @JoinColumn(name = "idMedic", referencedColumnName = "idMedic")
    private User idMedic;

    @ManyToOne
    @JoinColumn(name = "idAsistent", referencedColumnName = "idUser")
    private User idAsistent;

}
