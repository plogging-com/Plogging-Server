package com.plogging.domain.Board.service.comment;

import com.plogging.domain.Board.dto.board.request.createCommentReq;
import com.plogging.domain.Board.dto.board.request.modifyCommentReq;
import com.plogging.domain.Board.dto.board.response.CommentRes;
import com.plogging.global.dto.ApplicationResponse;

public interface CommentSercice {
    ApplicationResponse<CommentRes> createComment(createCommentReq createCommentReq);

    ApplicationResponse<CommentRes> delComment(Long commentId);

    ApplicationResponse<CommentRes> modifyComment(modifyCommentReq modifyCommentReq);
}
