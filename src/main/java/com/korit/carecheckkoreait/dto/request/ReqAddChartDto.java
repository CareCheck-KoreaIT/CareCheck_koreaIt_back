package com.korit.carecheckkoreait.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "차트 등록")
public class ReqAddChartDto {
    @Schema(description = "admin ID", example = "1", required = true)
    private int admId;
    @Schema(description = "환자명")
    private String content;
}
