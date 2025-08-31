package co.com.crediya.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Min(value = 1, message = "El salario debe ser mayor a 0")
    @Max(value = 15000000, message = "El salario debe ser menor o igual a 15,000,000")
    private int salary;
}
