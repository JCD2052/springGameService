package org.jcd2052.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeveloperStudioDto {
    private String studioName;
}
