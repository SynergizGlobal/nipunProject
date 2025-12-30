package com.nipun.nipunbackend.service;

public interface OTPService {

	String generateOTP(String phoneNumber, String email);

	boolean verifyOTP(String mobile, String otpCode);
	
	

}
