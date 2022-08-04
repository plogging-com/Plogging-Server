package com.plogging.domain.Board.service;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.global.dto.ApplicationResponse;

public interface BoardService {

    ApplicationResponse<BoardRes> boardCreate(createBoardReq createBoardReq);
}
