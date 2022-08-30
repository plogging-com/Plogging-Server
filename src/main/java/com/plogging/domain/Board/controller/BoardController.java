package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.request.getAllBoardsByCategoryReq;
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
    @ApiOperation(value = "게시글 등록", notes = "카테고리는 최소 1개 이상이어야 합니다. categoryName1 부터 카테고리를 등록해 주세요.")
    @PostMapping("")
    public ApplicationResponse<BoardRes> boardCreate(@ModelAttribute createBoardReq createBoardReq){
        return boardService.createBoard(createBoardReq);
    }

    /**
     * 전체 게시글 조회
     * @author 강신현
     */
    @ApiOperation(value = "전체 게시글 조회")
    @GetMapping("/all")
    public ApplicationResponse<Page<BoardAllRes>> getAllBoards(Pageable pageable){
        return boardService.getAllBoards(pageable);
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
     * 카테고리에 따른 전체 게시글 조회
     * @author 강신현
     */
    @ApiOperation(value = "카테고리에 따른 전체 게시글 조회")
    @GetMapping("/all/byCategory")
    public ApplicationResponse<Page<BoardAllRes>> getAllBoardsByCategory(Pageable pageable, getAllBoardsByCategoryReq getAllBoardsByCategoryReq){
        if(getAllBoardsByCategoryReq.getCategoryName3()==null){
            if(getAllBoardsByCategoryReq.getCategoryName2()==null){
                if(getAllBoardsByCategoryReq.getCategoryName1()==null){
                    return boardService.getAllBoards(pageable);
                }
                else{
                    return boardService.getAllBoardsBy1Category(pageable, getAllBoardsByCategoryReq);
                }
            }
            else{
                return boardService.getAllBoardsBy2Category(pageable, getAllBoardsByCategoryReq);
            }
        }
        else{
            return boardService.getAllBoardsBy3Category(pageable, getAllBoardsByCategoryReq);
        }

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
