package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.models.entity.MovieFavorite;
import com.lamnguyen.server.repositories.MovieFavoriteRepository;
import com.lamnguyen.server.services.MovieFavoriteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.persistence.EntityManager;
//import javax.persistence.Query;
import java.util.List;

@Service
public class MovieFavoriteServiceImpl implements MovieFavoriteService {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Integer> getFavoriteMoviesByCustomerId(Integer customerId) {
        Query query = entityManager.createQuery("SELECT distinct movie.id FROM MovieFavorite WHERE customer.id = :customerId");
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
}
