package co.com.crediya.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserRequestDTO {

    @NotNull(message = "El campo 'name' no puede ser nulo")
    @NotBlank(message = "El campo 'name' no puede estar vacío")
    private String name;

    @NotNull(message = "El campo 'lastName' no puede ser nulo")
    @NotBlank(message = "El campo 'lastName' no puede estar vacío")
    private String lastName;

    private LocalDate birthDate;

    private String address;

    private String phoneNumber;

    @NotNull(message = "El campo 'email' no puede ser nulo")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotNull(message = "El campo 'salary' no puede ser nulo")
    @Size(min = 1, max = 15000000, message = "El salario debe estar entre 1 y 15,000,000")
    @Positive(message = "El salario debe ser mayor que cero")
    private int salary;
}

