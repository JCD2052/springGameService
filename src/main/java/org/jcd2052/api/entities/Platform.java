package org.jcd2052.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.jcd2052.api.dto.PlatformDto;

import java.util.Set;

@Data
@Entity
@Table(name = "platform")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "platform_name", unique = true)
    private String platformName;

    @OneToMany(mappedBy = "platform")
    @ToString.Exclude
    @JsonIgnore
    private Set<Game> games;

    public PlatformDto toPlatformDto() {
        return PlatformDto.builder()
                .id(id)
                .platformName(platformName)
                .build();
    }
}