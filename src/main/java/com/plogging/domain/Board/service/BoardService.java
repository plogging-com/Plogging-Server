package com.plogging.domain.Board.service;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.entity.Board;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {

    ApplicationResponse<BoardRes> boardCreate(createBoardReq createBoardReq);

    ApplicationResponse<Page<BoardListRes>> getBoardList();
}
