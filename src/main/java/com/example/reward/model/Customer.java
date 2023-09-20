package com.example.reward.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @GeneratedValue
    private Long customerId;

    @NotBlank(message = "Customer's name can't be none!")
    private String customerName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private Set<Transaction> transactionSet = new HashSet<>();

    public int getRewardPointsByMonth(int month) {
        return transactionSet.stream()
                .filter(transaction -> transaction.getDate().getMonthValue() == month)
                .map(Transaction::getPoints)
                .reduce(0, Integer::sum);
    }

    public int getTotalPoints() {
        return transactionSet.stream()
                .map(Transaction::getPoints)
                .reduce(0, Integer::sum);
    }
}
