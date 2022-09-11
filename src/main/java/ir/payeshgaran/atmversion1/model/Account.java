package ir.payeshgaran.atmversion1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
public class Account extends BaseEntity {

    @Column(name = "accountNumber", unique = true)
    private String accountNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "remain", columnDefinition = "Decimal(10,0) default '100000'")
    private double remain = 100000;

    @OneToMany
    @JoinColumn
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
    }
}
