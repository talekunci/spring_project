package ua.goit.springproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(generator = "products_id_seq")
    private Long id;
    private String name;
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "producer_id", nullable = false)
    private Producer productProducer;

    public Product() {
    }

    public Product(Long id, String name, BigDecimal cost, Producer productProducer) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.productProducer = productProducer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId())
                && Objects.equals(getName(), product.getName())
                && Objects.equals(getCost(), product.getCost())
                && Objects.equals(getProductProducer(), product.getProductProducer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCost(), getProductProducer());
    }
}
