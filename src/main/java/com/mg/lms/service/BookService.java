package com.mg.lms.service;

import java.util.List;

import com.mg.lms.entity.Book;
import com.mg.lms.entity.User;
import com.mg.lms.repository.BookRepository;
import com.mg.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    public Book borrowBook(Long bookId, Long userId){
        Book book = findById(bookId);
        User user = userRepository.findById(userId).orElse(null);

        if(book != null && !book.isBorrowed() && user != null) {
            book.setBorrowedBy(user);
            book.setBorrowed(true);
        }

        return null;
    }

    public Book returnBook(Long bookId){
        Book book = findById(bookId);
        
        if(book != null && book.isBorrowed()){
            book.setBorrowedBy(null);
            book.setBorrowed(false);
            return save(book);
        }
        return null;
    }
}
