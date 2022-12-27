package org.jcd2052.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    @ManyToMany
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

//    @ManyToMany
//    @JoinTable(name = "game_rating",
//            joinColumns = @JoinColumn(name = "game_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<UserInfo> users;
}
