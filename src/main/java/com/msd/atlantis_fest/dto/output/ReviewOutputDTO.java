package com.msd.atlantis_fest.dto.output;

import lombok.Data;

@Data
public class ReviewOutputDTO {
    private Long id;
    private String targetType;
    private Long targetId;
    private Integer stars;
    private String comment;
    private String clientUsername;
}
