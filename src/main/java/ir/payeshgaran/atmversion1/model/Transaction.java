package ir.payeshgaran.atmversion1.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity {

    @Column(name = "depositorID")
    private Long depositorID;

    @Column(name = "receiverID")
    private Long receiverID;

    @Column(
            name = "balance",
            columnDefinition = "Decimal(10,2)"
    )
    private double balance;


}
