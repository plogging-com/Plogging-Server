package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.request.getAllBoardsByCategoryReq;
import com.plogging.domain.Board.dto.board.request.modifyBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardAllRes;
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
    @ApiOperation(value = "게시글 등록", notes = "swagger에서 이미지 여러장 업로드 시, 에러가 있으므로 postman에서 테스트 해주세요 (https://www.postman.com/solar-desert-882435/workspace/plogging/request/18177198-883b3d8e-82a7-4228-8bb6-c076ad75749a)")
    @PostMapping("")
    public ApplicationResponse<BoardRes> boardCreate(@ModelAttribute createBoardReq createBoardReq){
        return boardService.createBoard(createBoardReq);
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
     * 카테고리에 따른 전체 게시글 조회결
     * @author 강신현
     */
    @ApiOperation(value = "카테고리에 따른 게시글 조회", notes =  "카테고리를 입력하지 않으면 전체 게시글이 조회 됩니다.")
    @GetMapping("/all/byCategory")
    public ApplicationResponse<Page<BoardAllRes>> getAllBoardsByCategory(Pageable pageable, @ModelAttribute getAllBoardsByCategoryReq getAllBoardsByCategoryReq){
        return boardService.getAllBoardsByCategory(pageable, getAllBoardsByCategoryReq);
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

    /**
     * 게시글 수정
     * @author 강신현
     */
    @ApiOperation(value = "게시글 수정", notes = "swagger에서 이미지 여러장 업로드 시, 에러가 있으므로 postman에서 테스트 해주세요 (https://www.postman.com/solar-desert-882435/workspace/plogging/request/18177198-010a0bbb-0bdd-4910-856a-5c83035e5dce)")
    @PatchMapping("")
    public ApplicationResponse<BoardRes> modifyBoard(@ModelAttribute modifyBoardReq modifyBoardReq){
        return boardService.modifyBoard(modifyBoardReq);
    }
}
