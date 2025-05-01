package com.example.Controller;

import com.example.Model.IncomeDTO;
import com.example.Services.IncomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
    private final IncomeService incomeService;
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

//    @GetMapping
//    public List<IncomeDTO> getAllIncome() {
//        return incomeService.getAllIncomes();
//    }

    @GetMapping("/{incomeId}")
    public ResponseEntity<IncomeDTO> getIncomeById(@PathVariable Long incomeId) {
        return ResponseEntity.ok().body(incomeService.findIncomeById(incomeId).get());
    }

    @GetMapping("/user_id/{userId}")
    public List<IncomeDTO> getIncomeByUserId(@PathVariable Long userId) {
        return incomeService.findIncomeByUserId(userId);
    }

    @PostMapping
    public IncomeDTO createIncome(@RequestBody IncomeDTO incomeDTO) {
        return incomeService.save(incomeDTO);
    }

    @PutMapping("/{incomeId}")
    public ResponseEntity<IncomeDTO> updateIncome(@PathVariable Long incomeId, @RequestBody IncomeDTO incomeDTO) {
        try {
            IncomeDTO updatedIncome = incomeService.update(incomeDTO, incomeId);
            return ResponseEntity.ok(updatedIncome);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<String> deleteIncome(@PathVariable Long incomeId) {
        incomeService.deleteIncomeById(incomeId);
        return ResponseEntity.ok().body("Income deleted successfully !!");
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteIncomeByUserId(@PathVariable Long userId) {
        incomeService.deleteIncomeByUserId(userId);
        return ResponseEntity.ok().body("Income deleted successfully !!");
    }

}
