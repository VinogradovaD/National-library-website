package com.company.my_web.repo;

import com.company.my_web.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);
    Book findById(long id);
}
