package com.example.Model;

import java.time.LocalDate;

public record ExpenseDTO(Long id, Long userId, LocalDate date,String category, Double amount) {
}
