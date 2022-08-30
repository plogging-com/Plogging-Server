package com.plogging.domain.Board.service.comment;

import com.plogging.domain.Board.dto.board.request.createCommentReq;
import com.plogging.domain.Board.dto.board.request.getReCommentReq;
import com.plogging.domain.Board.dto.board.request.modifyCommentReq;
import com.plogging.domain.Board.dto.board.response.CommentRes;
import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Comment;
import com.plogging.domain.Board.exception.Board.NotFoundBoardException;
import com.plogging.domain.Board.exception.Comment.EmptyContentException;
import com.plogging.domain.Board.exception.Comment.NotFoundCommentException;
import com.plogging.domain.Board.repository.BoardRepository;
import com.plogging.domain.Board.repository.CommentRepository;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public ApplicationResponse<CommentRes> createComment(createCommentReq createCommentReq){

        if(createCommentReq.getContent() == null) throw new EmptyContentException();
        if(createCommentReq.getGroupNum() != null){
            if(!commentRepository.existsById(createCommentReq.getGroupNum())) throw new NotFoundCommentException();
        }

        User user = userRepository.findById(createCommentReq.getUserId()).orElseThrow(() -> new NotFoundUserException());
        Board board = boardRepository.findById(createCommentReq.getBoardId()).orElseThrow(() -> new NotFoundBoardException());
        board.plusCommentCnt();

        // 댓글일 경우 groupNum : null, 대댓글일 경우 gropNum : 댓글 id
        Comment comment = commentRepository.save(createCommentReq.toEntityComment(user, board, createCommentReq.getGroupNum()));

        CommentRes commentRes = CommentRes.create(comment);

        return ApplicationResponse.create("create",commentRes);
    }

    @Transactional
    @Override
    public ApplicationResponse<CommentRes> delComment(Long commentId){
        if(!commentRepository.existsById(commentId)) throw new NotFoundCommentException();

        Comment comment = commentRepository.findById(commentId).get();
        comment.changeCommentDelete();
        return ApplicationResponse.ok(CommentRes.create(comment));
    }

    @Transactional
    @Override
    public ApplicationResponse<CommentRes> modifyComment(modifyCommentReq modifyCommentReq){
        if(!commentRepository.existsById(modifyCommentReq.getCommentId())) throw new NotFoundCommentException();

        Comment comment = commentRepository.findById(modifyCommentReq.getCommentId()).get();
        comment.changeContent(modifyCommentReq.getNewContent());
        return ApplicationResponse.ok(CommentRes.create(comment));
    }

    @Override
    public ApplicationResponse<Slice<CommentRes>> getCommentList(Pageable pageable, Long boardId){
        if(!boardRepository.existsById(boardId)) throw new NotFoundBoardException();
        return ApplicationResponse.ok(commentRepository.findAllByBoardIdAndGroupNum(pageable, boardId, null).map(CommentRes::create));
    }

    @Override
    public ApplicationResponse<Slice<CommentRes>> getReCommentList(Pageable pageable, getReCommentReq getReCommentReq){
        if(!boardRepository.existsById(getReCommentReq.getBoardId())) throw new NotFoundBoardException();
        if(!commentRepository.existsById(getReCommentReq.getCommentId())) throw new NotFoundCommentException();
        return ApplicationResponse.ok(commentRepository.findAllByBoardIdAndGroupNum(pageable, getReCommentReq.getBoardId(), getReCommentReq.getCommentId()).map(CommentRes::create));
    }
}
