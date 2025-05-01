package com.example.Services;

import com.example.Model.IncomeDTO;

import java.util.List;
import java.util.Optional;

public interface IncomeService {
    Optional<IncomeDTO> findIncomeById(Long id);
    List<IncomeDTO> findIncomeByUserId(Long userId);
    void deleteIncomeById(Long id);
    void deleteIncomeByUserId(Long userId);
    IncomeDTO save(IncomeDTO incomeDTO);
    IncomeDTO update(IncomeDTO incomeDTO, Long id);
    Optional<IncomeDTO> findIncomeByUserIdWithMonth (Long userId, String month);
}
