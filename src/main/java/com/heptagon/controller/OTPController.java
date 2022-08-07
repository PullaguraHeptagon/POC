package com.heptagon.controller;

import com.heptagon.model.MessageResponse;
import com.heptagon.service.EmailService;
import com.heptagon.service.OTPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping(value = "/otp")
public class OTPController {

    private final Logger log = LoggerFactory.getLogger(OTPController.class);

    @Autowired
    public OTPService otpService;

    @Autowired
    public EmailService emailService;

    @GetMapping("/generateOtp")
    public ResponseEntity<?> generateOTP() throws Exception {
        log.info("Inside generateOTP");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        int otp = otpService.generateOTP(username);
        Map<String, String> replacements = new HashMap<>();
        replacements.put("user", username);
        replacements.put("otpnum", String.valueOf(otp));
        String message = emailService.getTemplate(replacements);
        emailService.sendOtpMessage("pullagura.lakshmidevi@heptagon.in", "Student Service - OTP", message);
        log.info("OTP generation is successful");
        return ResponseEntity.ok(new MessageResponse("OTP has been sent successfully"));
    }

    @GetMapping(value = "/validateOtp")
    public ResponseEntity<?> validateOtp(@RequestParam("otpnum") int otpnum) {
        log.info("Inside validateOtp");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String message = "";
        if (otpnum >= 0) {
            int serverOtp = otpService.getOtp(username);
            if (serverOtp > 0) {
                if (otpnum == serverOtp) {
                    otpService.clearOTP(username);
                    message = "OTP Validated Successfully";
                } else {
                    message = "OTP Validation failed";
                }
            }
        }
        log.info("OTP Validation completed");
        return ResponseEntity.ok(new MessageResponse(message));
    }
}
