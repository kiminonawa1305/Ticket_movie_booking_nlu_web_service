package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.Chair;
import com.lamnguyen.server.repositories.customs.ChairCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairRepository extends JpaRepository<Chair, Integer>, ChairCustomRepository {
    Chair findChairById(Integer id);
}
