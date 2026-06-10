package pk.ms.pasir_siwak_mateusz.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceDTO {

    private double totalIncome;
    private double totalExpense;
    private double balance;
}