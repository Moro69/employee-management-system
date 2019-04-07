package com.moro.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "education")
public class Education implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "education_id", nullable = false, unique = true)
    private Integer educationId;

    @NotNull
    @NotEmpty
    @Size(max = 128)
    @Column(name = "school_name", nullable = false)
    private String schoolName;

    @Size(max = 45)
    @Column
    private String degree;

    @Size(max = 45)
    @Column(name = "field_of_study")
    private String fieldOfStudy;

    @Size(max = 45)
    @Column
    private String grade;

    @NotNull
    @Size(min = 4, max = 4)
    @Column(name = "start_year", nullable = false)
    private String startYear;

    @NotNull
    @Size(min = 4, max = 4)
    @Column(name = "end_year", nullable = false)
    private String endYear;

    @Size(max = 128)
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;
}
