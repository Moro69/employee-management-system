package com.moro.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.moro.model.dto.RegistrationModel;
import com.moro.model.dto.UserDto;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "user_id", nullable = false, unique = true)
    private Integer userId;

    @NotEmpty
    @Size(max = 45)
    @Column
    private String name;

    @NotEmpty
    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    @Size(max = 128)
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty
    @Size(min = 8, max = 128)
    @Column
    @JsonIgnore
    private String password;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    @JsonManagedReference
    private Image image;

    @OneToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column
    private boolean enabled;

    public User(final RegistrationModel model) {
        this.name = model.getName();
        this.email = model.getEmail();
        this.password = model.getPassword();
    }

    public void mapFromDto(final UserDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
    }
}
