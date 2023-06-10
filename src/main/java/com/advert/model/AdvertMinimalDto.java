package com.advert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AdvertMinimalDto {
    private Long advertID;
    private String title;
    private Double price;
    private String imgLocation;

}
