package com.student.info.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.util.Set;

@Getter
@Setter
public class SignupRequest {
  @NotBlank
  private String username;

  @NotBlank
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  private String password;
}
