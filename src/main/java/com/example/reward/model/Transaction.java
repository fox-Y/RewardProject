package com.example.reward.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;

    private Double amount;

    private LocalDate date;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn
    @JsonIgnore
    private Customer customer;

    public int getPoints() {
        int points = 0;

        if (this.amount > 100) {
            points += 50;
            points += (int)(this.amount - 100) * 2;
        } else if (this.amount > 50) {
            points += (int)(this.amount - 50);
        }

        return points;
    }
}
