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

import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "developer_studio")
public class DeveloperStudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "studio_name", nullable = false, length = Integer.MAX_VALUE)
    private String studioName;

    @OneToMany(mappedBy = "gameDeveloperStudio")
    @ToString.Exclude
    @JsonBackReference
    private Set<GameInfo> gameInfos;
}