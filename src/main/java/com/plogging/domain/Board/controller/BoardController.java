package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.service.board.BoardServiceImpl;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags = "Board API")
@RequestMapping("api/board")
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    @PostMapping("")
    public ApplicationResponse<BoardRes> boardCreate(@ModelAttribute createBoardReq createBoardReq){
        return boardServiceImpl.createBoard(createBoardReq);
    }

    @GetMapping("/list")
    public ApplicationResponse<Page<BoardListRes>> getBoardList(Pageable pageable){
        return boardServiceImpl.getBoardList(pageable);
    }

    @PatchMapping("/del")
    public ApplicationResponse<Void> delBoard(Long id){
        return boardServiceImpl.delBoard(id);
    }
}
