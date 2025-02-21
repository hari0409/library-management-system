package com.example.library_management_system.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.library_management_system.ExceptionHandler.GlobalException;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookService {

    private BookRepository bookRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createOrUpdateBook(JsonNode bookData) {
        Book book = objectMapper.convertValue(bookData.get("book"), Book.class);
        if (book.getId() != null) {
            Optional<Book> existingBookOpt = bookRepository.findById(book.getId());
            if (existingBookOpt.isPresent()) {
                Book existingBook = existingBookOpt.get();
                existingBook.setIsbn(book.getIsbn());
                existingBook.setTitle(book.getTitle());
                existingBook.setPublicationYear(book.getPublicationYear());
                existingBook.setAvailableCopies(book.getAvailableCopies());
                existingBook.setTotalCopies(book.getTotalCopies());

                return bookRepository.save(existingBook);
            }
        }
        return bookRepository.save(book);
    }

    public List<Book> findBookByKey(String key) {
        List<String> keywords = Arrays.asList(key.toLowerCase().split("\\s+")); // Split based on space (one or more)
        String keyword1 = keywords.get(0);
        String keyword2 = keywords.size() > 1 ? keywords.get(1) : keyword1;
        String regex = String.join("|", keywords);
        return bookRepository.searchBooksWithRelevance(keyword1, keyword2, regex);
    }

    public Book findBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Book of id: " + id + " not found", 404));
    }

}
