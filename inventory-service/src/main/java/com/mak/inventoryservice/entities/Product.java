package com.mak.inventoryservice.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data  @NoArgsConstructor @AllArgsConstructor  @ToString
@ApiModel
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(min=3, max=20, message = "Nom trop long ou trop court.") // validation hibernate
    @NotBlank
    private String name;
    @Min(value = 0)
    private double price;
    private double quantity;
    @ApiModelProperty(position = 4, required = false, value = "false")
    private boolean selected;
    private transient LocalDate releaseDate;

  //  @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createTimestamp;

    @Column
    @LastModifiedDate
    private LocalDateTime updateTimestamp;

/*  @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "log_created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date logCreatedAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "log_updated_at", nullable = false)
    @LastModifiedDate
    private Date logUpdatedAt = new Date();*/


    public Product(@Length(min = 3, max = 20, message = "Nom trop long ou trop court.") @NotBlank String name, @Min(value = 0) double price, double quantity, boolean selected, LocalDate releaseDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.selected = selected;
        this.releaseDate = releaseDate;
    }
}
