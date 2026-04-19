package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewInputDTO {
    @NotBlank
    private String targetType;
    @NotNull private Long targetId;
    @NotNull
    private Integer stars;
    private String comment;
}
