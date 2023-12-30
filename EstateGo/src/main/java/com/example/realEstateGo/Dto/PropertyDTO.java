package com.example.realEstateGo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {
    
	private String name;
    private String description;
    private int price;
    private String propertyType;
    private String propertyImage;
    private String status;
    private String city;
    private String location;
    private String carpetArea;
}
