package org.jcd2052.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GameRatingDto {
    @JsonBackReference
    private GameDto game;
    @JsonBackReference
    private UserInfoDto user;
    private int rating;
    private String comment;
}
