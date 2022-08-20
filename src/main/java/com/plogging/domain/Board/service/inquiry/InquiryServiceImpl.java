package com.plogging.domain.Board.service.inquiry;

import com.plogging.domain.Board.dto.board.request.delInquiryReq;
import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Inquiry;
import com.plogging.domain.Board.exception.Board.NotFoundBoardException;
import com.plogging.domain.Board.exception.Inquiry.CanNotDeleteInquiry;
import com.plogging.domain.Board.repository.BoardRepository;
import com.plogging.domain.Board.repository.InquiryRepository;
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
public class InquiryServiceImpl implements InquiryService{

    private final BoardRepository boardRepository;
    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createInquiry(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NotFoundBoardException());

        Inquiry inquiry = Inquiry.builder()
                .user(board.getUser())
                .board(board)
                .build();

        inquiryRepository.save(inquiry);
    }

    @Transactional
    @Override
    public ApplicationResponse<Void> delInquiry(delInquiryReq delInquiryReq){
        User user = userRepository.findById(delInquiryReq.getUserId()).orElseThrow(() -> new NotFoundUserException());
        Board board = boardRepository.findById(delInquiryReq.getBoardId()).orElseThrow(() -> new NotFoundBoardException());

        Optional<Inquiry> inquiry = inquiryRepository.findByUserAndBoard(user, board);

        // 사전에 조회 기록이 있는 경우에만 조회기록 삭제 가능
        if(inquiry.isEmpty()) throw new CanNotDeleteInquiry();

        board.getInquiry().remove(inquiry.get());
        user.getInquiry().remove(inquiry.get());

        inquiryRepository.delete(inquiry.get());

        return ApplicationResponse.ok();
    }

}
