package com.plogging.domain.Board.service.comment;

import com.plogging.domain.Board.dto.board.request.createCommentReq;
import com.plogging.domain.Board.dto.board.request.getReCommentReq;
import com.plogging.domain.Board.dto.board.request.modifyCommentReq;
import com.plogging.domain.Board.dto.board.response.CommentRes;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CommentService {
    ApplicationResponse<CommentRes> createComment(createCommentReq createCommentReq);

    ApplicationResponse<CommentRes> delComment(Long commentId);

    ApplicationResponse<CommentRes> modifyComment(modifyCommentReq modifyCommentReq);

    ApplicationResponse<Slice<CommentRes>> getCommentList(Pageable pageable, Long boardId);

    ApplicationResponse<Slice<CommentRes>> getReCommentList(Pageable pageable, getReCommentReq getReCommentReq);
}
