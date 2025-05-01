package com.example.Controller;

import com.example.Model.ExpenseDTO;
import com.example.Services.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ExpenseDTO saveExpense(@RequestBody ExpenseDTO expenseDTO) {
        return expenseService.save(expenseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.findExpenseById(id).get());
    }

    @GetMapping("/user/{userid}")
    public List<ExpenseDTO> getExpenseByUserId(@PathVariable Long userid) {
        return expenseService.findExpenseByUserId(userid);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable Long id) {
        expenseService.deleteExpenseById(id);
        return ResponseEntity.ok().body("Expense deleted successfully !!");
    }

    @DeleteMapping("/user/{userid}")
    public ResponseEntity<String> deleteExpenseByUserId(@PathVariable Long userid) {
        expenseService.deleteExpenseByUserId(userid);
        return ResponseEntity.ok().body("Expense deleted successfully !!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@RequestBody ExpenseDTO expenseDTO, @PathVariable Long id) {
        try{
            ExpenseDTO updatedExpense = expenseService.update(expenseDTO, id);
            return ResponseEntity.ok(updatedExpense);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }


}
