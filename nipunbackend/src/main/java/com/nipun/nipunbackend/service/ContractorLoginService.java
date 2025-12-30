package com.nipun.nipunbackend.service;

import com.nipun.nipunbackend.dto.ContractorRegRequest;
import com.nipun.nipunbackend.model.nipun.ContractorReg;

public interface ContractorLoginService {

	String loginWithOtp(String email, String phone);

	boolean verifyOtp(String phone, String otp);

	ContractorReg registerContractor(ContractorRegRequest req);

}
