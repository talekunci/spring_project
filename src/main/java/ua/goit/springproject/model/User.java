package ua.goit.springproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_id_seq")
    @Column(nullable = false)
    private Long id;
    private String email;
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;

    public User() {
    }

    public User(Long id, String email, String password, String lastName, String firstName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(
                getId(), user.getId())
                && Objects.equals(getEmail(), user.getEmail())
                && Objects.equals(getLastName(), user.getLastName())
                && Objects.equals(getFirstName(), user.getFirstName()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getEmail(),
                getLastName(),
                getFirstName()
        );
    }
}
