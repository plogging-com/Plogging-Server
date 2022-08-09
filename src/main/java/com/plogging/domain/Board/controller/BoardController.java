package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.service.BoardServiceImpl;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    @PostMapping("/board")
    public ApplicationResponse<BoardRes> boardCreate(@ModelAttribute createBoardReq createBoardReq){
        return boardServiceImpl.boardCreate(createBoardReq);
    }

    @GetMapping("/board-list")
    public ApplicationResponse<Page<BoardListRes>> getBoardList(){
        return boardServiceImpl.getBoardList();
    }
}
