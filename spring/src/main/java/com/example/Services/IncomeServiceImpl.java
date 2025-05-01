package com.example.Services;

import com.example.Model.Income;
import com.example.Model.IncomeDTO;
import com.example.Model.User;
import com.example.Repository.IncomeRepository;
import com.example.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImpl implements IncomeService {
    private final UserRepository userRepository;
    private IncomeRepository incomeRepository;

    public IncomeServiceImpl(IncomeRepository incomeRepository, UserRepository userRepository) {
        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<IncomeDTO> findIncomeById(Long id) {
        return incomeRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<IncomeDTO> findIncomeByUserId(Long userId) {
        return incomeRepository.findIncomeByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteIncomeById(Long id) {
        incomeRepository.deleteById(id);
    }

    @Override
    public void deleteIncomeByUserId(Long userId) {
        List<Income> incomes = incomeRepository.findIncomeByUserId(userId);
        incomeRepository.deleteAll(incomes);
    }

    @Override
    public IncomeDTO save(IncomeDTO incomeDTO) {
        Income income = convertToEntity(incomeDTO);
        Income saved = incomeRepository.save(income);
        return convertToDTO(saved);
    }

    @Override
    public IncomeDTO update(IncomeDTO incomeDTO,Long id) {
        Income income = incomeRepository.findById(id).orElseThrow();
        income.setAmount(incomeDTO.amount());
        income.setDate(incomeDTO.date());
        income.setCurrency(incomeDTO.currency());
        if(incomeDTO.userId() != null) {
            User user= userRepository.findById(incomeDTO.userId()).orElseThrow();
            income.setUser(user);
        }
        income.setType(incomeDTO.type());
        return convertToDTO(incomeRepository.save(income));
    }

    @Override
    public Optional<IncomeDTO> findIncomeByUserIdWithMonth(Long userId, String month) {
        return Optional.empty();
    }

    private IncomeDTO convertToDTO(Income income) {
        return new IncomeDTO(income.getId(), income.getUser().getId(),income.getType(),income.getCurrency(),income.getAmount(),income.getDate());
    }

    private Income convertToEntity(IncomeDTO incomeDTO) {
        Income income = new Income();
        income.setAmount(incomeDTO.amount());
        income.setCurrency(incomeDTO.currency());
        income.setDate(incomeDTO.date());
        income.setType(incomeDTO.type());
        income.setId(incomeDTO.id());
        if(incomeDTO.userId() != null) {
            User user= userRepository.findById(incomeDTO.userId()).orElseThrow();
            income.setUser(user);
        }
        return income;
    }
}
