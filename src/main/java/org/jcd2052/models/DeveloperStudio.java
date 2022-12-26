package org.jcd2052.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@NoArgsConstructor
@Entity(name = "developer_studio")
@Data
@ToString(exclude = "games")
@EqualsAndHashCode(exclude = "games")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "studioId")
public class DeveloperStudio {
    @OneToMany(mappedBy = "developerStudio")
    private Set<GameInfo> games;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id")
    private int studioId;
    @Column(name = "studio_name")
    private String studioName;
}
