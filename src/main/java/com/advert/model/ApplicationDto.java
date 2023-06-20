package com.advert.model;

import com.advert.repository.model.AdvertEntity;
import jdk.jfr.Registered;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApplicationDto {
    private Long applicationId;
    private AdvertMinimalDto advertMinimalDto;
    private String username;
}