package com.example.coffeapp.dto.order;

import com.example.coffeapp.entity.payday.PayDay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ExpenseDTO implements Serializable {

    private Long id;

    private String commit;

    private double sum;

    private PayDay payDay;
}
