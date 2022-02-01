package fr.capeb.backend.riskevaluator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCompte;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    private String password;

    @Column(name = "lastname")
    private String firstname;

    @Column(name = "is_valid")
    private boolean isValid;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

}
