package com.nipun.nipunbackend.dto;

import lombok.Data;

@Data
public class ContractorRegRequest {

	private String companyName;

	private String contactPerson;

	private String mobile;

	private String email;

	private String address;

	private String pmisContractorId;

}
