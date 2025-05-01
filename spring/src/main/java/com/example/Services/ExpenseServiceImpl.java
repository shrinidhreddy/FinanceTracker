package com.example.Services;

import com.example.Model.Expense;
import com.example.Model.ExpenseDTO;
import com.example.Model.User;
import com.example.Repository.ExpenseRepository;
import com.example.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final UserRepository userRepository;
    private ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ExpenseDTO> findExpenseByUserId(Long userId) {
        return expenseRepository.findExpenseByUserId(userId).stream().map(this::convertToDTO).collect(Collectors.toList()) ;
    }

    @Override
    public Optional<ExpenseDTO> findExpenseById(Long id) {
        return expenseRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public void deleteExpenseById(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public void deleteExpenseByUserId(Long userId) {
        List<Expense> expenses = expenseRepository.findExpenseByUserId(userId);

        expenseRepository.deleteAll(expenses);
    }

    @Override
    public ExpenseDTO save(ExpenseDTO expenseDTO) {
        Expense expense = convertToEntity(expenseDTO);
        Expense savedExpense = expenseRepository.save(expense);

        return convertToDTO(savedExpense);
    }

    @Override
    public ExpenseDTO update(ExpenseDTO expenseDTO, Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow();
        if(expenseDTO.userId() != null) {
            User user = userRepository.findById(expenseDTO.userId()).orElseThrow();
            expense.setUser(user);
        }
        expense.setDate(expenseDTO.date());
        expense.setAmount(expenseDTO.amount());
        expense.setCategory(expenseDTO.category());
        expenseRepository.save(expense);
        return convertToDTO(expense);
    }

    private ExpenseDTO convertToDTO(Expense expense) {
        return new ExpenseDTO(expense.getId(),expense.getUser().getId(), expense.getDate(),expense.getCategory(),expense.getAmount());
    }
    private Expense convertToEntity(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        expense.setId(expenseDTO.id());
        if(expenseDTO.userId() != null) {
            User user = userRepository.findById(expenseDTO.userId()).orElseThrow();
            expense.setUser(user);
        }
        expense.setDate(expenseDTO.date());
        expense.setCategory(expenseDTO.category());
        expense.setAmount(expenseDTO.amount());
        return expense;
    }
}
