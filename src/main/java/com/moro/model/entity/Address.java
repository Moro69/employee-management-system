package com.moro.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "address_id", nullable = false, unique = true)
    private Integer addressId;

    @NotNull
    @NotEmpty
    @Size(max = 45)
    @Column(nullable = false)
    private String country;

    @NotNull
    @NotEmpty
    @Size(max = 45)
    @Column(nullable = false)
    private String city;

    @NotNull
    @NotEmpty
    @Size(max = 45)
    @Column(nullable = false)
    private String street;

    @NotNull
    @NotEmpty
    @Size(max = 45)
    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Size(max = 45)
    @Column(name = "apartment_number")
    private String apartmentNumber;

    @NotNull
    @NotEmpty
    @Size(max = 45)
    @Column(name = "postal_code", nullable = false)
    private String postalCode;
}
