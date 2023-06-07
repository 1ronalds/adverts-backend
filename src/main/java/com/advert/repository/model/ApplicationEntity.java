package com.advert.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application")
public class ApplicationEntity {
    @Column(name = "advert_id")
    private Long advertId;

    @Column(name = "user_id")
    private Long userId;

}