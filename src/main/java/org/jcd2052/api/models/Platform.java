package org.jcd2052.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "platform")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "platform_name", nullable = false, length = Integer.MAX_VALUE)
    private String platformName;

    @OneToMany(mappedBy = "platform")
    @ToString.Exclude
    @JsonBackReference
    private Set<Game> games;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platform platform = (Platform) o;
        return id.equals(platform.id) && platformName.equals(platform.platformName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, platformName);
    }
}