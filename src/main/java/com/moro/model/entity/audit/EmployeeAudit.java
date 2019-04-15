package com.moro.model.entity.audit;

import com.moro.model.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_audit")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeAudit implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "audit_id", nullable = false, unique = true)
    private Integer auditId;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private String position;

    @Column
    private Integer salary;

    @Column(name = "passport_identification_number")
    private String passportIdentificationNumber;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public static EmployeeAudit of(final Employee employee) {
        return EmployeeAudit.builder()
                .email(employee.getEmail())
                .employeeId(employee.getEmployeeId())
                .name(employee.getName())
                .passportIdentificationNumber(employee.getPassportIdentificationNumber())
                .phoneNumber(employee.getPhoneNumber())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .build();
    }
}
