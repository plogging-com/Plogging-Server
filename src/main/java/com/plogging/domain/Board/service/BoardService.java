package com.plogging.domain.Board.service;

import com.plogging.domain.Board.dto.board.request.BoardReq;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.repository.BoardRepository;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public ApplicationResponse<BoardRes> boardCreate(BoardReq boardReq) {
        String imageURL = "~~"; // s3Service.makeImage(questReq.getPhoto());
        User user = userRepository.getOne(boardReq.getUser_idx());

        Board board = boardRepository.save(boardReq.toEntity(imageURL, user));

        BoardRes boardRes = BoardRes.create(board);
        return ApplicationResponse.create("created", boardRes);
    }
}
