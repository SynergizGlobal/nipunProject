package com.nipun.nipunbackend.repository.nipun;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nipun.nipunbackend.model.nipun.OTP;

@Repository
public interface OTPRepository extends JpaRepository <OTP, Long> {

    Optional<OTP> findByPhoneNumberAndOtpCode(String phoneNumber, String otpCode);


	
	
}
