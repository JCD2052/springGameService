package org.jcd2052.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity(name = "platform")
@ToString(exclude = "games")
@EqualsAndHashCode(exclude = "games")
public class Platform {
    @ManyToMany(mappedBy = "platforms", fetch = FetchType.EAGER)
    private Set<GameInfo> games;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_id")
    private int platformId;
    @Column(name = "platform_name")
    private String platformName;
}
