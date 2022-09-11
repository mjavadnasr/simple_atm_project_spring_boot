package ir.payeshgaran.atmversion1.model;

import lombok.Data;
import javax.persistence.*;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_name")
    @SequenceGenerator(
            name = "seq_name",
            sequenceName = "seq_name",
            allocationSize = 1
    )
    private Long id;
}
