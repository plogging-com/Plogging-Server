package com.plogging.domain.Board.service.board;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    ApplicationResponse<BoardRes> createBoard(createBoardReq createBoardReq);

    ApplicationResponse<Page<BoardListRes>> getBoardList(Pageable pageable);

    ApplicationResponse<Void> delBoard(Long id);

    ApplicationResponse<BoardRes> getBoard(Long boardId);
}
