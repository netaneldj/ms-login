package com.jamilis.login.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private String name;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(.+)@(.+)[.](.+)$",
            message = "Invalid email format. Email format must be like: aaaaaaa@undominio.algo")
    private String email;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(?=(?:[^A-Z]*[A-Z]){1}[^A-Z]*$)(?=(?:[^0-9]*[0-9]){2}[^0-9]*$).{8,12}$",
            message = "Password must contain 1 uppercase, 2 digits and lowercases. Must have length between 8 and 12")
    private String password;
    private List<PhoneDto> phones;
}
