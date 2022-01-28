package com.example.coffeapp.entity.payday;

import com.example.coffeapp.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "pay_day")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayDay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "open_time", nullable = false)
    private ZonedDateTime openTime;

    @Column(name = "close_time")
    private ZonedDateTime closeTime;

    @Column(name = "sum_all")
    private Double sumAll;

    @Column(name = "sum_cash")
    private Double sumCash;

    @Column(name = "sum_not_cash")
    private Double sumNotCash;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
