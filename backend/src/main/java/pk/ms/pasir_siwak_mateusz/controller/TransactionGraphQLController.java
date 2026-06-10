package pk.ms.pasir_siwak_mateusz.controller;

import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import pk.ms.pasir_siwak_mateusz.dto.BalanceDTO;
import pk.ms.pasir_siwak_mateusz.dto.TransactionDTO;
import pk.ms.pasir_siwak_mateusz.model.Transaction;
import pk.ms.pasir_siwak_mateusz.model.User;
import pk.ms.pasir_siwak_mateusz.service.TransactionService;

import java.util.List;

@Controller
public class TransactionGraphQLController {

    private final TransactionService transactionService;

    public TransactionGraphQLController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @QueryMapping
    public List<Transaction> transactions() {
        return transactionService.getAllTransactions();
    }

    @QueryMapping
    public BalanceDTO userBalance(@Argument Double days) {
        User user = transactionService.getCurrentUser();
        return transactionService.getUserBalance(user, days);
    }

    @MutationMapping
    public Transaction addTransaction(@Valid @Argument TransactionDTO transactionDTO) {
        return transactionService.createTransaction(transactionDTO);
    }

    @MutationMapping
    public Transaction updateTransaction(@Argument Long id, @Valid @Argument TransactionDTO transactionDTO) {
        return transactionService.updateTransaction(id, transactionDTO);
    }

    @MutationMapping
    public Boolean deleteTransaction(@Argument Long id) {
        transactionService.deleteTransaction(id);
        return true;
    }
}