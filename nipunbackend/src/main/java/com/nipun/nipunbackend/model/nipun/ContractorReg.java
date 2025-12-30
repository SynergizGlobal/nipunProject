package com.nipun.nipunbackend.model.nipun;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "contractors")
@Data
public class ContractorReg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contractorId;

	private String companyName;
	private String contactPerson;

	@Column(unique = true)
	private String mobile;

	private String email;
	private String address;
	private String pmisContractorId;

	@Enumerated(EnumType.STRING)
	private Status status;

	private LocalDateTime registrationDate = LocalDateTime.now();

	public enum Status {
		Active, Inactive
	}

}
