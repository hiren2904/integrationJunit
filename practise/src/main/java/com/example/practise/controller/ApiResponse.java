package com.example.practise.controller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse<T> {

	private Boolean success;
	private T response; 
	private Integer total;
	private ErrorResponse error;
	private String timestamp;
}
