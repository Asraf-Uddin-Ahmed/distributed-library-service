package com.epam.distributedlibraryservice.controllers;

import com.epam.distributedlibraryservice.dtos.loan.LoanMapper;
import com.epam.distributedlibraryservice.dtos.loan.LoanRequestDto;
import com.epam.distributedlibraryservice.entities.Book;
import com.epam.distributedlibraryservice.entities.Loan;
import com.epam.distributedlibraryservice.entities.User;
import com.epam.distributedlibraryservice.services.BookService;
import com.epam.distributedlibraryservice.services.LoanService;
import com.epam.distributedlibraryservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoanController {

    private final BookService bookService;
    private final UserService userService;
    private final LoanMapper loanMapper;
    private final LoanService loanService;

    @GetMapping("/books/{id}/loan-request/initiate")
    public String showLoanForm(@PathVariable("id") int id, Model model) {
        // Create a new LoanRequestDTO object and add it to the model
        model.addAttribute("loanRequestDto", new LoanRequestDto());
        model.addAttribute("actionUrl", "/books/" + id + "/loan-request/initiate");
        return "loan-form";
    }

    @PostMapping("/books/{id}/loan-request/initiate")
    public String initiateLoanRequest(@PathVariable("id") int id,
                                      @ModelAttribute("loanRequestDto") @Valid LoanRequestDto loanRequestDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "loan-form";
        }

        Book book = bookService.getById(id);
        User user = userService.getById(loanRequestDto.getUserId());
        Loan loan = loanMapper.getLoanEntity(loanRequestDto, book, user, userService.getCurrentUser());
        // Call the loanService to initiate the loan request
        loanService.save(loan);

        return "redirect:/books/loan-requests/sent";
    }

    @GetMapping("/books/loan-requests/sent")
    public String showLoanSent(Model model) {
        List<Loan> loans = loanService.getAllSentLoans();
        model.addAttribute("loans", loans);
        return "loan-sent";
    }

    @GetMapping("/books/loan-requests/received")
    public String showLoanReceived(Model model) {
        List<Loan> loans = loanService.getAllReceivedLoans();
        model.addAttribute("loans", loans);
        return "loan-received";
    }

    @PostMapping("/books/loan-requests/received/{id}")
    public String handleLoanRequestAction(@PathVariable("id") Integer id, @RequestParam("action") String action) {
        Loan loan = loanService.getById(id);
        // Logic to handle loan request action based on "action" parameter
        if ("accept".equals(action)) {
            loanService.acceptLoanRequest(loan);
        } else if ("reject".equals(action)) {
            loanService.rejectLoanRequest(loan);
        }

        // Redirect to appropriate page after handling loan request action
        return "redirect:/books/loan-requests/received";
    }

}
