package com.korit.carecheckkoreait.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class ReqChangeEmailDto {
    @Schema(description = "이메일 변경")
    private String email;
}
