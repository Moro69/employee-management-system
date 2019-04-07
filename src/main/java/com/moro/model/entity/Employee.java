package com.moro.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "employee_id", nullable = false, unique = true)
    private Integer employeeId;

    @NotNull
    @NotEmpty
    @Size(max = 128)
    @Column(nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Size(max = 45)
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @NotEmpty
    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    @Size(max = 45)
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @Size(max = 45)
    @Column(nullable = false)
    private String position;

    @NotNull
    @Column(nullable = false)
    private Integer salary;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @NotNull
    @NotEmpty
    @Size(max = 45)
    @Column(name = "passport_identification_number", nullable = false)
    private String passportIdentificationNumber;

    @OneToMany(mappedBy = "employee",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Education> educations = new HashSet<>();

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    @Valid
    private Address address;

    @NotNull
    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public void addEducation(final Education education) {
        education.setEmployee(this);
        educations.add(education);
    }
}
