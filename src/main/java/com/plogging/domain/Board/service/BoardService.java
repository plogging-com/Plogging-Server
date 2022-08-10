package com.plogging.domain.Board.service;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface BoardService {

    ApplicationResponse<BoardRes> boardCreate(createBoardReq createBoardReq);

    ApplicationResponse<Page<BoardListRes>> getBoardList();

    ApplicationResponse<Void> delBoard(Long id);
}
