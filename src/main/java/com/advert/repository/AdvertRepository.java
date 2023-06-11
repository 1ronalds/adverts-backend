package com.advert.repository;

import com.advert.repository.model.AdvertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<AdvertEntity, Long> {
    List<AdvertEntity> findAllByUserId(Long userId);
}

