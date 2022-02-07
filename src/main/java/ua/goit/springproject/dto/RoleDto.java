package ua.goit.springproject.dto;

import lombok.Data;
import ua.goit.springproject.model.Role;
import ua.goit.springproject.validation.IsUnique;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@IsUnique(
        model = Role.class,
        field = "name",
        message = "Role with this name already exist."
)
public class RoleDto {

    @NotNull
    @Min(0)
    private Long id;

    @NotEmpty
    private String name;

}
