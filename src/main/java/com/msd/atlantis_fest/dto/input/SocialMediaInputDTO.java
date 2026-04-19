package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SocialMediaInputDTO {
    @NotBlank
    private String tipo;
    @NotBlank private String url;
    @NotNull
    private Long artistId;
}
