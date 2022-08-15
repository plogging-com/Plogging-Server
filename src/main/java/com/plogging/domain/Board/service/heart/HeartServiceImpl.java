package com.plogging.domain.Board.service.heart;

import com.plogging.domain.Board.dto.board.request.createHeartReq;
import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Heart;
import com.plogging.domain.Board.repository.BoardRepository;
import com.plogging.domain.Board.repository.HeartRepository;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Board board = boardRepository.findById(createHeartReq.getBoard_idx()).get();
        board.plusHeartCnt();

        User user = userRepository.findById(board.getUser().getId()).get();

        Heart heart = heartRepository.save(createHeartReq.toEntity(user, board));

        return ApplicationResponse.ok();
    }

//    @Transactional
//    @Override
//    public ApplicationResponse<Void> delHeart(){
//
//    }
}
