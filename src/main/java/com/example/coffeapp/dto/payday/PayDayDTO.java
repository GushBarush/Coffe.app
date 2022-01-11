package com.example.coffeapp.dto.payday;

import com.example.coffeapp.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PayDayDTO implements Serializable {

    private Long id;

    private boolean active;

    private Timestamp openTime;

    private Timestamp closeTime;

    private Double sumAll;

    private Double sumCash;

    private Double sumNotCash;

    private User user;
}
