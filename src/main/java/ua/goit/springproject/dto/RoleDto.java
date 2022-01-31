package ua.goit.springproject.dto;

import lombok.Data;
import ua.goit.springproject.model.Role;
import ua.goit.springproject.validation.IsUnique;

import javax.validation.constraints.NotEmpty;

@Data
@IsUnique(
        model = Role.class,
        field = "name",
        message = "Role with this name already exist."
)
public class RoleDto {

    @NotEmpty
    private String name;

}
