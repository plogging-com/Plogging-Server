package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createCommentReq;
import com.plogging.domain.Board.dto.board.response.CommentRes;
import com.plogging.domain.Board.service.comment.CommentSercice;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
