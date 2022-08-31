package com.plogging.domain.Board.service.board;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.request.getAllBoardsByCategoryReq;
import com.plogging.domain.Board.dto.board.response.BoardAllRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    ApplicationResponse<BoardRes> createBoard(createBoardReq createBoardReq);

    ApplicationResponse<Page<BoardAllRes>> getAllBoards(Pageable pageable);

    ApplicationResponse<Page<BoardAllRes>> getAllBoardsBy1Category(Pageable pageable, getAllBoardsByCategoryReq getAllBoardsByCategoryReq);
    ApplicationResponse<Page<BoardAllRes>> getAllBoardsBy2Category(Pageable pageable, getAllBoardsByCategoryReq getAllBoardsByCategoryReq);
    ApplicationResponse<Page<BoardAllRes>> getAllBoardsBy3Category(Pageable pageable, getAllBoardsByCategoryReq getAllBoardsByCategoryReq);

    ApplicationResponse<Void> delBoard(Long id);

    ApplicationResponse<BoardRes> getBoard(Long boardId);
}
