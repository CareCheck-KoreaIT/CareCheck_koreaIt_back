package com.korit.carecheckkoreait.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReqAddChartDto {
    @Schema(description = "admin ID", example = "1", required = true)
    private int admId;
    @Schema(description = "content")
    private String content;
}
