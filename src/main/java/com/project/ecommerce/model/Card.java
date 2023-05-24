package com.project.ecommerce.model;

import com.project.ecommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.lang.String;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="card")
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true,nullable = false)
    String cardNo;

    int cvv;

    Date expiryDate;

    @Enumerated(EnumType.STRING)
    CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
