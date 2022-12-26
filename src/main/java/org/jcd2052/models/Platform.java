package org.jcd2052.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity(name = "platform")
@ToString(exclude = "games")
@EqualsAndHashCode(exclude = "games")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "platformId")
public class Platform {
    @ManyToMany(mappedBy = "platforms")
    private Set<GameInfo> games;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_id")
    private int platformId;
    @Column(name = "platform_name")
    private String platformName;
}
