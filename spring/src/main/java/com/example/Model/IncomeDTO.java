package com.example.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record IncomeDTO(Long id, Long userId, String type, String currency, Double amount, LocalDate date) {
}
