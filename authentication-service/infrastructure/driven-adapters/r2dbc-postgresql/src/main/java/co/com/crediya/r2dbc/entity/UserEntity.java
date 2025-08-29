package co.com.crediya.r2dbc.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class UserEntity {

    @Id
    @Column("user_id")
    private UUID id;

    private String name;

    private String lastName;

    private LocalDate birthDate;

    private String address;

    private String phoneNumber;

    private String email;

    private int salary;
}
