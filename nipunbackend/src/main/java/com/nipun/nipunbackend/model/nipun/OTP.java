package com.nipun.nipunbackend.model.nipun;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "otp_store")
@Data
public class OTP {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String phoneNumber;
	
	private String email;

	@Column(nullable = false)
	private String otpCode;

	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	// Optional: expiry time
	@Column(nullable = false)
	private LocalDateTime expiresAt;

}
