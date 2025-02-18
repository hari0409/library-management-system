package com.example.library_management_system.entity;

import java.time.LocalDateTime;

import com.example.library_management_system.db_enum.BookReturnType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrowing_records")
public class BorrowingRecord {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "borrowed_date")
    private LocalDateTime borrowedDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "status")
    private BookReturnType status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDateTime borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public BookReturnType getStatus() {
        return status;
    }

    public void setStatus(BookReturnType status) {
        this.status = status;
    }

    public BorrowingRecord() {

    }

    public BorrowingRecord(Integer id, LocalDateTime borrowedDate, LocalDateTime dueDate, LocalDateTime returnDate,
            BookReturnType status) {
        this.id = id;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "BorrowingRecord [id=" + id + ", borrowedDate=" + borrowedDate + ", dueDate=" + dueDate + ", returnDate="
                + returnDate + ", status=" + status + "]";
    }

}
