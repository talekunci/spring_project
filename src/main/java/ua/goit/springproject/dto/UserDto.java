package ua.goit.springproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.goit.springproject.model.Role;
import ua.goit.springproject.model.User;
import ua.goit.springproject.validation.IsUnique;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@IsUnique(
        model = User.class,
        field = "email",
        message = "User's email not unique."
)
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotEmpty
    private String email;
    private String firstName;
    private String lastName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Role role;

    @NotNull
    @Min(0)
    private Long roleId;

}
