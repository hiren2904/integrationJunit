package com.example.practise.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {

	private List<SearchRequestDto> searchRequestDto;
	
	private GlobalOperator globaloperator;
	
	public enum GlobalOperator{
		AND,OR;
	}
}
