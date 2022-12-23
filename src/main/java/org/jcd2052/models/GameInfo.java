package org.jcd2052.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity(name = "game_info")
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "platforms")
public class GameInfo {
    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "game_name")
    private String name;
    @Column(name = "game_description")
    private String description;
    //TODO shout be date with format mm.dd.YYYY.
    @Column(name = "game_release_date")
    private int releaseYear;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Game",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id"))
    private Set<Platform> platforms;
    @ManyToOne
    @JoinColumn(name = "game_genre_id", referencedColumnName = "genre_id")
    private Genre genre;
    @ManyToOne
    @JoinColumn(name = "game_developer_studio_id", referencedColumnName = "studio_id")
    private DeveloperStudio developerStudio;
}
