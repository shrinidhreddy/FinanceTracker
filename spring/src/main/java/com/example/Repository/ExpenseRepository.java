package com.example.Repository;

import com.example.Model.Expense;
import com.example.Model.ExpenseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findExpenseByUserId(Long id);
}
