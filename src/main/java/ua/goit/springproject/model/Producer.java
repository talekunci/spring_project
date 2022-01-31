package ua.goit.springproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "producers")
public class Producer {

    @Id
    @GeneratedValue(generator = "producers_id_seq")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "productProducer")
    private Set<Product> producerProductList;

    public Producer() {
    }

    public Producer(Long id, String name, Set<Product> producerProductList) {
        this.id = id;
        this.name = name;
        this.producerProductList = producerProductList;
    }

    public Set<Product> getProductList() {
        return producerProductList;
    }

    public void setProductList(Set<Product> producerProductList) {
        this.producerProductList = producerProductList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(getId(), producer.getId())
                && Objects.equals(getName(), producer.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
