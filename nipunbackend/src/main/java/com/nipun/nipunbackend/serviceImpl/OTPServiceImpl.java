package com.nipun.nipunbackend.serviceImpl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nipun.nipunbackend.model.nipun.OTP;
import com.nipun.nipunbackend.repository.nipun.OTPRepository;
import com.nipun.nipunbackend.service.OTPService;

@Service
public class OTPServiceImpl implements OTPService {

	@Autowired
	private OTPRepository otpRepository;

	@Override
	public String generateOTP(String phoneNumber, String email) {

		if (phoneNumber == null || phoneNumber.isBlank()) {
			throw new IllegalArgumentException("Phone number cannot be null");
		}

		String otpCode = String.valueOf(new Random().nextInt(900000) + 100000);

		OTP otp = new OTP();
		otp.setPhoneNumber(phoneNumber);
	    otp.setEmail(email); // set email if available

		otp.setOtpCode(otpCode);
		otp.setCreatedAt(LocalDateTime.now());
		otp.setExpiresAt(LocalDateTime.now().plusMinutes(5));

		otpRepository.save(otp);

		// TODO: Integrate SMS gateway
		System.out.println("OTP sent to " + phoneNumber + " : " + otpCode);

		return otpCode;
	}

	@Override
	public boolean verifyOTP(String phoneNumber, String otpCode) {
		return otpRepository.findByPhoneNumberAndOtpCode(phoneNumber, otpCode)
				.filter(o -> o.getExpiresAt().isAfter(LocalDateTime.now())).isPresent();
	}

}
