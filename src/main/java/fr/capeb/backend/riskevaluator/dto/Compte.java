package fr.capeb.backend.riskevaluator.dto;

import fr.capeb.backend.riskevaluator.model.EntrepriseEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Compte {
    private Integer idCompte;
    private Integer noEntreprise;
    private Boolean isAdmin;
    private String mail;
    private String pwd;
    private String name;
    private String firstName;
    private Boolean isValid;
    private Date creationDate;
    private Date lastLogin;
    private Boolean isCgu;
    private Boolean isForgotpass;
    private String isTempPassword;

}
