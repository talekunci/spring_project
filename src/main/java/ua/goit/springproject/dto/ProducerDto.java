package ua.goit.springproject.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.goit.springproject.model.Producer;
import ua.goit.springproject.validation.IsUnique;

import javax.validation.constraints.NotEmpty;

@Data
@IsUnique(
        model = Producer.class,
        field = "name",
        message = "Producer's name not unique."
)
public class ProducerDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotEmpty
    private String name;

}
