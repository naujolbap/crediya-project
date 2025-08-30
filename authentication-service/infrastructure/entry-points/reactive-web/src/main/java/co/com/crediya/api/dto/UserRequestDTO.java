package co.com.crediya.api.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserRequestDTO {

    private String name;

    private String lastName;

    private LocalDate birthDate;

    private String address;

    private String phoneNumber;

    private String email;

    private int salary;
}

