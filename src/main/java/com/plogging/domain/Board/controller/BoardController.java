package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.service.board.BoardServiceImpl;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "게시글 등록", notes = "카테고리는 최소 1개 이상이어야 합니다. categoryName1 부터 카테고리를 등록해 주세요.")
    @PostMapping("")
    public ApplicationResponse<BoardRes> boardCreate(@ModelAttribute createBoardReq createBoardReq){
        return boardServiceImpl.createBoard(createBoardReq);
    }

    @ApiOperation(value = "전체 게시글 조회")
    @GetMapping("/list")
    public ApplicationResponse<Page<BoardListRes>> getBoardList(Pageable pageable){
        return boardServiceImpl.getBoardList(pageable);
    }

    @ApiOperation(value = "게시글 삭제")
    @PatchMapping("/del")
    public ApplicationResponse<Void> delBoard(Long id){
        return boardServiceImpl.delBoard(id);
    }
}
