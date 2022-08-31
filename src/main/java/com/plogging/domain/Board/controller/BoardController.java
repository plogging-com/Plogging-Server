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
    @ApiOperation(value = "게시글 등록", notes = "swagger에서 이미지 여러장 업로드 시, 에러가 있으므로 postman에서 테스트 해주세요 (https://solar-desert-882435.postman.co/workspace/My-Workspace~167b7ee1-c23b-4ee3-8c78-29d79a1698e1/request/18177198-883b3d8e-82a7-4228-8bb6-c076ad75749a)")
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
