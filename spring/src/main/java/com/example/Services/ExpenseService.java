package com.example.Services;

import com.example.Model.ExpenseDTO;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<ExpenseDTO> findExpenseByUserId(Long userId);
    Optional<ExpenseDTO> findExpenseById(Long id);
    void deleteExpenseById(Long id);
    void deleteExpenseByUserId(Long userId);
    ExpenseDTO save(ExpenseDTO expenseDTO);
    ExpenseDTO update(ExpenseDTO expenseDTO, Long id);
}
