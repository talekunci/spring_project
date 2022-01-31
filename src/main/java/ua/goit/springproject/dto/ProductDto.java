package ua.goit.springproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.goit.springproject.model.Producer;
import ua.goit.springproject.model.Product;
import ua.goit.springproject.validation.IsUnique;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@IsUnique(
        model = Product.class,
        field = "name",
        message = "Product with this name already exist."
)
public class ProductDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    @Min(0)
    private Long producerId;
    @Min(0)
    private BigDecimal cost;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Producer producer;

}
