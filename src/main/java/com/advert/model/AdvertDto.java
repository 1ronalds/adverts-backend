package com.advert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AdvertDto {
    private Long advertID;
    private String title;
    private String description;
    private double price;
    private String imgLocation;
    private String userName;

}
