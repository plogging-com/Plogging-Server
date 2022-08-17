package com.plogging.domain.Board.service.board;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.exception.Board.OverMaxContentLength;
import com.plogging.domain.Board.repository.BoardRepository;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public ApplicationResponse<BoardRes> createBoard(createBoardReq createBoardReq) {
        // 글자수가 700자를 넘는 경우 에러
        if(createBoardReq.getContent().length() > 700) throw new OverMaxContentLength();

        String imageURL = "~~"; // s3Service.makeImage(questReq.getPhoto());

        User user = userRepository.findById(createBoardReq.getUserId()).orElseThrow(() -> new NotFoundUserException());
        Board board = boardRepository.save(createBoardReq.toEntityWithPhoto(imageURL, user));

        BoardRes boardRes = BoardRes.create(board);
        return ApplicationResponse.create("created", boardRes);
    }

    @Override
    public ApplicationResponse<Page<BoardListRes>> getBoardList(Pageable pageable) {
        return ApplicationResponse.ok(boardRepository.findAll(pageable).map(BoardListRes::create));
    }

    @Transactional
    @Override
    public ApplicationResponse<Void> delBoard(Long id){
        Board board = boardRepository.findById(id).get();
        board.changeBoardDelete();
        return ApplicationResponse.ok();
    }

}
