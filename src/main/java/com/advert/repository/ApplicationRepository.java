package com.advert.repository;

import com.advert.repository.model.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
    List<ApplicationEntity> findAllByUserId(Long userId);

    List<ApplicationEntity> findAllByAdvertId(Long advertID);
}
