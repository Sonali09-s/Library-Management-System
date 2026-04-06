package com.expense.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.dao.entity.UploadedBooks;

@Repository
public interface UploadedBooksRepo extends JpaRepository<UploadedBooks, Long>{

}
