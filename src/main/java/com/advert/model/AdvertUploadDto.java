package com.advert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AdvertUploadDto {
    private Long advertID;

    @NotEmpty
    @Size(max = 50)
    private String title;

    @NotEmpty
    @Size(max = 500)
    private String description;

    @NotNull
    private double price;

    @NotEmpty
    @Size(max = 50)
    private String imgName;

    @NotEmpty
    private String imgData;

    private Long userID;
}
