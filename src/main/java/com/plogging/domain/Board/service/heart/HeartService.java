package com.plogging.domain.Board.service.heart;

import com.plogging.domain.Board.dto.board.request.createHeartReq;
import com.plogging.global.dto.ApplicationResponse;

public interface HeartService {
    ApplicationResponse<Void> createHeart(createHeartReq createHeartReq);

//    ApplicationResponse<Void> delHeart();
}
