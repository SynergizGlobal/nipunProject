package com.nipun.nipunbackend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nipun.nipunbackend.dto.ContractorRegRequest;
import com.nipun.nipunbackend.model.nipun.ContractorReg;
import com.nipun.nipunbackend.service.ContractorLoginService;

@RestController
@RequestMapping("/api/contractor")
@CrossOrigin(origins = "http://localhost:3000")
public class ContractorController {
	@Autowired
	private ContractorLoginService loginService;

	@PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> body) {

        try {
            String mobile = body.get("mobile");
            String email = body.get("email");

            String status = loginService.loginWithOtp(email, mobile);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "status", status
            ));

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", ex.getMessage()
            ));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Map.of(
                    "success", false,
                    "message", "Something went wrong. Please try again."
            ));
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> body) {

        try {
            String mobile = body.get("mobile");
            String otp = body.get("otp");

            boolean valid = loginService.verifyOtp(mobile, otp);

            if (!valid) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Invalid or expired OTP"
                ));
            }

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "OTP verified successfully"
            ));

        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Map.of(
                    "success", false,
                    "message", "OTP verification failed"
            ));
        }
    }
    
    // Register new contractor
    @PostMapping("/register")
    public ResponseEntity<?> registerContractor(@RequestBody ContractorRegRequest req) {
        try {
            ContractorReg contractor = loginService.registerContractor(req);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "contractorId", contractor.getContractorId(),
                    "message", "Contractor registered successfully. Please login."
            ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", ex.getMessage()
            ));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Map.of(
                    "success", false,
                    "message", "Registration failed"
            ));
        }
    }
}
