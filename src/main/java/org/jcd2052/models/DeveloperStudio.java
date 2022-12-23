package org.jcd2052.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@NoArgsConstructor
@Entity(name = "developer_studio")
@Data
@ToString(exclude = "games")
@EqualsAndHashCode(exclude = "games")
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
