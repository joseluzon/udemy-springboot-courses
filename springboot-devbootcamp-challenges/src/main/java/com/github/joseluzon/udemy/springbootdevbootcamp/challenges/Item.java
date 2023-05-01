package com.github.joseluzon.udemy.springbootdevbootcamp.challenges;

import java.util.Date;
import java.util.UUID;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Item {
    @NotBlank(message = "Choose a category")
    private String category;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Price cannot be empty")
    @Min(value = 0, message = "Price cannot be negative")
    private Double price;
    @NotNull(message = "Discount cannot be empty")
    @Min(value = 0, message = "Discount cannot be negative")
    private Double discount;
    @NotNull(message = "Date cannot be empty")
    @Past(message = "Date must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Builder.Default
    private UUID uuid = UUID.randomUUID(); 
}
