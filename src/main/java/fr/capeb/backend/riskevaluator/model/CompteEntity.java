package fr.capeb.backend.riskevaluator.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "compte")
public class CompteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compte")
    private Integer idCompte;

    @OneToMany(mappedBy = "entreprise",cascade = CascadeType.ALL)
    private List<EvaluationEntity> evaluations;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "mail")
    private String mail;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "is_cgu")
    private Boolean isCgu;

    @Column(name = "is_forgotpass")
    private Boolean isForgotpass;

    @Column(name = "is_temp_password")
    private String isTempPassword;
}
