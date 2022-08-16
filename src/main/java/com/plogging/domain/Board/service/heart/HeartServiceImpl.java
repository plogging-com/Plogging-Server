package com.plogging.domain.Board.service.heart;

import com.plogging.domain.Board.dto.board.request.createHeartReq;
import com.plogging.domain.Board.dto.board.request.delHeartReq;
import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Heart;
import com.plogging.domain.Board.exception.Board.NotFoundBoardException;
import com.plogging.domain.Board.exception.Heart.AlreadyHearted;
import com.plogging.domain.Board.exception.Heart.CanNotDeleteHeart;
import com.plogging.domain.Board.repository.BoardRepository;
import com.plogging.domain.Board.repository.HeartRepository;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class HeartServiceImpl implements HeartService{

    private final HeartRepository heartRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public ApplicationResponse<Void> createHeart(createHeartReq createHeartReq){
        User user = userRepository.findById(createHeartReq.getUserId()).orElseThrow(() -> new NotFoundUserException());
        Board board = boardRepository.findById(createHeartReq.getBoardId()).orElseThrow(() -> new NotFoundBoardException());

        // 이미 좋아요 되어 있는 경우 에러
        if(heartRepository.findByUserAndBoard(user, board).isPresent()) throw new AlreadyHearted();

        Heart heart = Heart.builder()
                .user(user)
                .board(board)
                .build();

        heartRepository.save(heart);

        return ApplicationResponse.ok();
    }

    @Transactional
    @Override
    public ApplicationResponse<Void> delHeart(delHeartReq delHeartReq){
        User user = userRepository.findById(delHeartReq.getUserId()).orElseThrow(() -> new NotFoundUserException());
        Board board = boardRepository.findById(delHeartReq.getBoardId()).orElseThrow(() -> new NotFoundBoardException());

        Optional<Heart> heart = heartRepository.findByUserAndBoard(user, board);

        // 사전에 좋아요 되어 있는 경우에만 좋아요 취소 가능
        if(heart.isEmpty()) throw new CanNotDeleteHeart();

        board.getHearts().remove(heart.get());
        user.getHearts().remove(heart.get());

        heartRepository.delete(heart.get());

        return ApplicationResponse.ok();
    }

}
