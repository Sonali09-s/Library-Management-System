package com.expense.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.dao.entity.DumpTable;

public interface DumpTableRepo extends JpaRepository<DumpTable, Long> {

}
