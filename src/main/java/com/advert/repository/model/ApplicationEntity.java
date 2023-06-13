package com.advert.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application")
public class ApplicationEntity {
    @Id
    @Column(name = "advert_id")
    private Long advertId;

    @NaturalId
    @Column(name = "user_id")
    private Long userId;

}