package com.heptagon.model;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	private HttpStatus status;
	private String message;
}
