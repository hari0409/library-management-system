package com.example.library_management_system.routes;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.misc.GlobalConstant;
import com.example.library_management_system.misc.ResponseObject;
import com.example.library_management_system.service.BookService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/v1/book")
public class BookRoute {

    private BookService bookService;

    public BookRoute(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<ResponseObject<?>> createOrUpdateBook(@RequestBody JsonNode bookData) {
        return ResponseEntity.status(201)
                .body(new ResponseObject<Book>(GlobalConstant.SUCCESS, bookService.createOrUpdateBook(bookData)));
    }

    @GetMapping
    public ResponseEntity<ResponseObject<?>> getBookByIsbnOrTitleV2(@RequestParam String key) {
        return ResponseEntity.status(200)
                .body(new ResponseObject<List<Book>>(GlobalConstant.SUCCESS, bookService.findBookByKey(key)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<?>> getBookById(@PathVariable Integer id) {
        return ResponseEntity.status(200)
                .body(new ResponseObject<Book>(GlobalConstant.SUCCESS, bookService.findBookById(id)));
    }

}
