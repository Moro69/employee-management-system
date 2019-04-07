package com.moro.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "department_id", nullable = false, unique = true)
    private Integer departmentId;

    @NotNull
    @NotEmpty
    @Size(max = 45)
    @Column(unique = true, nullable = false)
    private String name;

    @Size(max = 128)
    @Column
    private String description;

    @Size(max = 45)
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "formation_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate formationDate;

    @OneToOne(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Supervisor supervisor;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();
}
