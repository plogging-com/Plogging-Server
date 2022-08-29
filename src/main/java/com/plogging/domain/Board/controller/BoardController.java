package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.service.board.BoardService;
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

    private final BoardService boardService;

    /**
     * 게시글 등록
     * @author 강신현
     */
    @ApiOperation(value = "게시글 등록", notes = "")
    @PostMapping("/")
    public ApplicationResponse<BoardRes> boardCreate(@ModelAttribute createBoardReq createBoardReq){
        return boardService.createBoard(createBoardReq);
    }

    /**
     * 전체 게시글 조회
     * @author 강신현
     */
    @ApiOperation(value = "전체 게시글 조회", notes = "")
    @GetMapping("/list")
    public ApplicationResponse<Page<BoardListRes>> getBoardList(Pageable pageable){
        return boardService.getBoardList(pageable);
    }

    /**
     * 게시글 하나 조회
     * @author 강신현
     */
    @ApiOperation(value = "게시글 하나 조회", notes = "")
    @GetMapping("{boardId}")
    public ApplicationResponse<BoardRes> getBoardList(@PathVariable Long boardId){
        return boardService.getBoard(boardId);
    }

    /**
     * 게시글 삭제
     * @author 강신현
     */
    @ApiOperation(value = "게시글 삭제", notes = "")
    @PatchMapping("/del")
    public ApplicationResponse<Void> delBoard(Long id){
        return boardService.delBoard(id);
    }
}
