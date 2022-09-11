package ir.payeshgaran.atmversion1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String accountNumber;
    private String password;
    private double remain;


}
