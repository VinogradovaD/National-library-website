package com.company.my_web.repo;

import com.company.my_web.models.Book;
import com.company.my_web.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findAllByAuthorUsername(String name);
}
