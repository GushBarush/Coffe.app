package com.example.coffeapp.entity.order;

import com.example.coffeapp.entity.payday.PayDay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "expense", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "commit", nullable = false)
    private String commit;

    @Column(name = "sum")
    private double sum;

    @ManyToOne
    @JoinColumn(name = "pay_day_id", nullable = false)
    private PayDay payDay;
}
