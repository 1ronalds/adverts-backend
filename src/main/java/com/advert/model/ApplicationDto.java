package com.advert.model;

import com.advert.repository.model.AdvertEntity;
import lombok.Data;

@Data
public class ApplicationDto {
    private AdvertMinimalDto advertMinimalDto;
    private String username;
}