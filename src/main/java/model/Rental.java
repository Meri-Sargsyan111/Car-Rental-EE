package model;

import enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental {
    private int id;
    private int carId;
    private int customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
    private RentalStatus status;
    private Customer customer;
}

