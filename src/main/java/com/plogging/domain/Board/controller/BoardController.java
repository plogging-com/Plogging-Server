package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.BoardReq;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.service.BoardService;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public ApplicationResponse<BoardRes> boardCreate(@ModelAttribute BoardReq boardReq){
        return boardService.boardCreate(boardReq);
    }
}
