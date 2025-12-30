package com.nipun.nipunbackend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nipun.nipunbackend.dto.ContractorRegRequest;
import com.nipun.nipunbackend.model.nipun.ContractorReg;
import com.nipun.nipunbackend.model.pmis.Contractor;
import com.nipun.nipunbackend.repository.nipun.NipunContractorRepository;
import com.nipun.nipunbackend.repository.pmis.ContractorRepository;
import com.nipun.nipunbackend.service.ContractorLoginService;
import com.nipun.nipunbackend.service.OTPService;

@Service
public class ContractorLoginServiceImpl implements ContractorLoginService {

	@Autowired
	private ContractorRepository pmisRepo;

	@Autowired
	private NipunContractorRepository nipunRepo;

	@Autowired
	private OTPService otpService;

	@Override
    public String loginWithOtp(String email, String mobile) {

        if (mobile == null || mobile.isBlank()) {
            throw new IllegalArgumentException("Mobile number is required");
        }

        // 1️⃣ PMIS check
        if (pmisRepo.existsByPhoneNumber(mobile)) {
            otpService.generateOTP(mobile, email);
            return "OTP sent to mobile no";
        }

        // 2️⃣ Nipun check
        if (nipunRepo.existsByMobile(mobile)) {
        	otpService.generateOTP(mobile, email);
            return "otp sent to mobile no";
        }

        // 3️⃣ Not registered
        return "mobile Number not found please Register the contractor";
    }

	@Override
    public boolean verifyOtp(String mobile, String otpCode) {

        if (mobile == null || otpCode == null) {
            throw new IllegalArgumentException("Mobile and OTP are required");
        }

        return otpService.verifyOTP(mobile, otpCode);
    }
	
	@Override
    public ContractorReg registerContractor(ContractorRegRequest req) {

        if (nipunRepo.existsByMobile(req.getMobile()))
            throw new IllegalArgumentException("Mobile already registered");

        ContractorReg contractor = new ContractorReg();
        contractor.setCompanyName(req.getCompanyName());
        contractor.setContactPerson(req.getContactPerson());
        contractor.setMobile(req.getMobile());
        contractor.setEmail(req.getEmail());
        contractor.setAddress(req.getAddress());
        contractor.setPmisContractorId(req.getPmisContractorId());
        contractor.setStatus(ContractorReg.Status.Active);

        return nipunRepo.save(contractor);
    }
}
