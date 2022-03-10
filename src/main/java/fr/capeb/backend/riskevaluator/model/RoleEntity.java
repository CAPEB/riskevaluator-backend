package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.enumeration.ERole;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, unique = true)
	private ERole name;

	public RoleEntity() {

	}

	public RoleEntity(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}