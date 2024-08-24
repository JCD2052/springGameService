package org.jcd2052.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jcd2052.api.dto.DeveloperStudioDto;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "developer_studio")
public class DeveloperStudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "studio_name", unique = true)
    private String studioName;

    @OneToMany(mappedBy = "developerStudio")
    @ToString.Exclude
    @JsonBackReference
    private Set<Game> games;

    public DeveloperStudioDto toDeveloperStudioDto() {
        return DeveloperStudioDto.builder()
                .id(id)
                .developerStudioName(studioName)
                .build();
    }
}