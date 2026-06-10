package pk.ms.pasir_siwak_mateusz.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotBlank(message = "E-mail jest wymagany")
    @Email
    private String email;

    @NotBlank(message = "Hasło jest wymagane")
    private String password;
}
