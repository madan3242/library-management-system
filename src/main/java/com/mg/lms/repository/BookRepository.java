package com.mg.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mg.lms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
