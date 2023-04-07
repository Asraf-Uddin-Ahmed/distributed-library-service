package com.epam.distributedlibraryservice.services;

import com.epam.distributedlibraryservice.constants.LoanStatus;
import com.epam.distributedlibraryservice.entities.Book;
import com.epam.distributedlibraryservice.entities.Loan;
import com.epam.distributedlibraryservice.entities.User;
import com.epam.distributedlibraryservice.repositories.BookRepository;
import com.epam.distributedlibraryservice.repositories.LoanRepository;
import com.epam.distributedlibraryservice.repositories.ReservationRepository;
import com.epam.distributedlibraryservice.services.impl.LoanServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
class LoanServiceTest {

    @InjectMocks
    private LoanServiceImpl loanService;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserService userService;

    @Test
    void whenServiceSaveMethodCalled_thenRepositorySaveMethodShouldBeCalled() {
        // Arrange
        Loan loan = new Loan();

        // Act
        loanService.save(loan);

        // Assert
        verify(loanRepository, times(1)).save(loan);
    }

    @Test
    void whenLoanGetById_thenLoanShouldBeFound() {
        // Arrange
        int id = 1;
        Loan expectedLoan = new Loan();
        when(loanRepository.findById(id)).thenReturn(Optional.of(expectedLoan));

        // Act
        Loan actualLoan = loanService.getById(id);

        // Assert
        assertEquals(expectedLoan, actualLoan);
    }

    @Test
    void whenLoanGetByIdNotExists_thenNoSuchElementExceptionThrown() {
        // Arrange
        int id = 1;
        when(loanRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> loanService.getById(id));
    }

    @Test
    void whenGetAllReceivedLoans_thenLoansShouldBeFound() {
        // Arrange
        User currentUser = new User();
        currentUser.setId(1);
        List<Loan> expectedLoans = Arrays.asList(new Loan(), new Loan());
        when(userService.getCurrentUser()).thenReturn(currentUser);
        when(loanRepository.findByUserIdAndStatusAndDueDateAfter(anyInt(), any(LoanStatus.class), any(Date.class))).thenReturn(expectedLoans);

        // Act
        List<Loan> actualLoans = loanService.getAllReceivedLoans();

        // Assert
        assertEquals(expectedLoans, actualLoans);
    }

    @Test
    void whenGetAllSentLoans_thenLoansShouldBeFound() {
        // Arrange
        User currentUser = new User();
        currentUser.setId(1);
        List<Loan> expectedLoans = Arrays.asList(new Loan(), new Loan());
        when(userService.getCurrentUser()).thenReturn(currentUser);
        when(loanRepository.findByFromUserIdAndStatusAndDueDateAfter(anyInt(), any(LoanStatus.class), any(Date.class))).thenReturn(expectedLoans);

        // Act
        List<Loan> actualLoans = loanService.getAllSentLoans();

        // Assert
        assertEquals(expectedLoans, actualLoans);
    }

    @Test
    void whenRejectLoanRequest_thenLoanStatusShouldBeRejected() {
        // Arrange
        Loan loan = new Loan();
        Date currentDate = new Date();

        // Act
        loanService.rejectLoanRequest(loan);

        // Assert
        verify(loanRepository, times(1)).save(loan);
        assertNotNull(loan.getLoanDate());
        assertEquals(LoanStatus.REJECTED, loan.getStatus());
    }

    @Test
    void whenAcceptLoanRequest_thenLoanStatusShouldBeAccepted() {
        // Arrange
        Loan loan = new Loan();
        Book book = new Book();
        loan.setBook(book);
        User currentUser = new User();
        currentUser.setId(1);
        loan.setUser(currentUser);

        // Act
        loanService.acceptLoanRequest(loan);

        // Assert
        verify(loanRepository, times(1)).save(loan);
        verify(bookRepository, times(1)).save(book);
        assertNotNull(loan.getLoanDate());
        assertEquals(LoanStatus.ACCEPTED, loan.getStatus());
        assertEquals(currentUser, book.getCurrentKeeper());
    }

    @Test
    void whenGetLoanHistory_thenLoansShouldBeFound() {
        // Arrange
        User currentUser = new User();
        currentUser.setId(1);
        List<Loan> expectedLoans = Arrays.asList(new Loan(), new Loan());
        when(userService.getCurrentUser()).thenReturn(currentUser);
        when(loanRepository.findByUserIdOrFromUserId(currentUser.getId(), currentUser.getId())).thenReturn(expectedLoans);

        // Act
        List<Loan> actualLoans = loanService.getLoanHistory();

        // Assert
        assertEquals(expectedLoans, actualLoans);
    }

    @Test
    void whenGetLoansByUserId_thenLoansShouldBeFound() {
        // Arrange
        int userId = 1;
        List<Loan> expectedLoans = Arrays.asList(new Loan(), new Loan());
        when(loanRepository.findByUserId(userId)).thenReturn(expectedLoans);

        // Act
        List<Loan> actualLoans = loanService.getLoansByUserId(userId);

        // Assert
        assertEquals(expectedLoans, actualLoans);
    }

}
