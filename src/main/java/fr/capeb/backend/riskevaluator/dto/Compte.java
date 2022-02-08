package fr.capeb.backend.riskevaluator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.sql.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Compte {
    private Integer idCompte;
    @JsonIgnoreProperties({"scoreCategories","compte","entreprise"})
    private List<Evaluation> evaluations;
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
