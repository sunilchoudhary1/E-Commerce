package com.project.ecommerce.dto.RequestDto;

import com.project.ecommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {

    String mobNo;

    String cardNo;

    int cvv;

    Date expiryDate;

    CardType cardType;
}
