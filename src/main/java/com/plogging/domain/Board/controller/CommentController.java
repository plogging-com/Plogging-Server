package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createCommentReq;
import com.plogging.domain.Board.dto.board.request.getReCommentReq;
import com.plogging.domain.Board.dto.board.request.modifyCommentReq;
import com.plogging.domain.Board.dto.board.response.CommentRes;
import com.plogging.domain.Board.service.comment.CommentSercice;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags = "Comment API")
@RequestMapping("api/comment")
public class CommentController {

    private final CommentSercice commentSercice;

    /**
     * 댓글, 대댓글 달기
     * @author 강신현
     */
    @ApiOperation(value = "댓글, 대댓글 달기", notes = "댓글은 groupNum이 null, 대댓글은 groupNum이 댓글의 id 값 입니다.")
    @PostMapping("")
    public ApplicationResponse<CommentRes> createComment(@ModelAttribute createCommentReq createCommentReq){
        return commentSercice.createComment(createCommentReq);
    }

    /**
     * 댓글 조회
     * @author 강신현
     */
    @ApiOperation(value = "댓글 조회", notes = "게시글 id에 따른 댓글(groupNum : null)이 표시 됩니다.")
    @GetMapping("{boardId}")
    public ApplicationResponse<Slice<CommentRes>> getCommentList(Pageable pageable, @PathVariable Long boardId){
        return commentSercice.getCommentList(pageable, boardId);
    }

    /**
     * 대댓글 조회
     * @author 강신현
     */
    @ApiOperation(value = "대댓글 조회", notes = "게시글 id 와 댓글 id에 따른 대댓글(groupNum : 댓글 id)이 표시 됩니다.")
    @GetMapping("recomment")
    public ApplicationResponse<Slice<CommentRes>> getCommentList(Pageable pageable, @ModelAttribute getReCommentReq getReCommentReq){
        return commentSercice.getReCommentList(pageable, getReCommentReq);
    }

    /**
     * 댓글 삭제
     * @author 강신현
     */
    @ApiOperation(value = "댓글 삭제", notes = "삭제시, 댓글의 status 가 DELETE로 변경됩니다.")
    @PatchMapping("/del/{commentId}")
    public ApplicationResponse<CommentRes> delComment(@PathVariable Long commentId) {
        return commentSercice.delComment(commentId);
    }

    /**
     * 댓글 수정
     * @author 강신현
     */
    @ApiOperation(value = "댓글 수정", notes = "댓글 내용(content)을 수정합니다.")
    @PatchMapping("/modify/{commentId}")
    public ApplicationResponse<CommentRes> modifyComment(@ModelAttribute modifyCommentReq modifyCommentReq) {
        return commentSercice.modifyComment(modifyCommentReq);
    }
}
