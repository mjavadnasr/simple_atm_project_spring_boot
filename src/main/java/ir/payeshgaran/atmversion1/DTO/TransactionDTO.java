package ir.payeshgaran.atmversion1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDTO {

    private String depositor;

    @NotBlank(message = "receiver cannot be blank")
    @Size(min = 4,message = "receiver must be at least 5 char")
    private String receiver;

    @Min(value = 5000 , message = "balance must be at least 5000")
    private double balance;
}
