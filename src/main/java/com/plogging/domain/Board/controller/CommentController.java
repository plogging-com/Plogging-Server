package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createCommentReq;
import com.plogging.domain.Board.dto.board.response.CommentRes;
import com.plogging.domain.Board.service.comment.CommentSercice;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags = "Comment API")
@RequestMapping("api/comment")
public class CommentController {

    private final CommentSercice commentSercice;

    /**
     * 댓글 달기
     * @author 강신현
     */
    @ApiOperation(value = "댓글 달기", notes = "댓글은 groupNum이 null, 대댓글은 groupNum이 댓글의 id 값 입니다.")
    @PostMapping("")
    public ApplicationResponse<CommentRes> createComment(@ModelAttribute createCommentReq createCommentReq){
        return commentSercice.createComment(createCommentReq);
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
}
