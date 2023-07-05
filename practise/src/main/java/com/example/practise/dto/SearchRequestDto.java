package com.example.practise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {

	String column;
	String value;
	
	Operation operation;
	public enum Operation{
		LIKE,EQUAL, IN, LESS_THAN, GREATER_THAN, JOIN;
	}
}
