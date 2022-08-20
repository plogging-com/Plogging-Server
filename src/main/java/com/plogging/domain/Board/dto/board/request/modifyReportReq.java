package com.plogging.domain.Board.dto.board.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class modifyReportReq {
    private Long reportId;
    private String newContent;
}
