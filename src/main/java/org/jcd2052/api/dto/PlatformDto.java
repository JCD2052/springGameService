package org.jcd2052.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PlatformDto {
    private int id;
    private String platformName;
}
