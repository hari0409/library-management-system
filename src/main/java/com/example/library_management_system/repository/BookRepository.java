package com.example.library_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library_management_system.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // Sub-String Count by finding out how many times it has been replaced to find
    // the relavence score and return result based on like & regex.
    @Query(value = "SELECT b.*, " +
            "( (LENGTH(LOWER(b.title)) - LENGTH(REPLACE(LOWER(b.title), :keyword1, ''))) / LENGTH(:keyword1) + " +
            "  (LENGTH(LOWER(b.title)) - LENGTH(REPLACE(LOWER(b.title), :keyword2, ''))) / LENGTH(:keyword2) + " +
            "  (LENGTH(LOWER(b.isbn)) - LENGTH(REPLACE(LOWER(b.isbn), :keyword1, ''))) / LENGTH(:keyword1) + " +
            "  (LENGTH(LOWER(b.isbn)) - LENGTH(REPLACE(LOWER(b.isbn), :keyword2, ''))) / LENGTH(:keyword2) " +
            ") AS relevance_score " +
            "FROM books b " +
            "WHERE LOWER(b.isbn) LIKE LOWER(CONCAT('%', :keyword1, '%')) " +
            "OR LOWER(b.isbn) LIKE LOWER(CONCAT('%', :keyword2, '%')) " +
            "OR LOWER(b.title) REGEXP :regex " +
            "ORDER BY relevance_score DESC", nativeQuery = true)
    List<Book> searchBooksWithRelevance(@Param("keyword1") String keyword1,
            @Param("keyword2") String keyword2,
            @Param("regex") String regex);
}
