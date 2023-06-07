package com.advert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AdvertUploadDto {
    private Long advertID;
    private String title;
    private String description;
    private double price;
    private String imgName;
    private String imgData;
    private Long userID;
}
