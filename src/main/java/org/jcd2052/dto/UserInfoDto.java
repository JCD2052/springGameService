package org.jcd2052.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private String userName;
    @JsonManagedReference("userGameRatings")
    private Set<GameRatingDto> gameRatings = new HashSet<>();
}
