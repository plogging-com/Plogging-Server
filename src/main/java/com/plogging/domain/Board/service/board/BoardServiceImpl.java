package com.plogging.domain.Board.service.board;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.repository.BoardRepository;
import com.plogging.domain.Board.repository.CommentRepository;
import com.plogging.domain.Board.repository.HeartRepository;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        String imageURL = "~~"; // s3Service.makeImage(questReq.getPhoto());

        User user = userRepository.findById(createBoardReq.getUser_idx()).get();

        Board board = boardRepository.save(createBoardReq.toEntityWithPhoto(imageURL, user));

        BoardRes boardRes = BoardRes.create(board);
        return ApplicationResponse.create("created", boardRes);
    }

    @Override
    public ApplicationResponse<Page<BoardListRes>> getBoardList() {
        PageRequest pageRequest = PageRequest.of(0,5, Sort.by(Sort.Direction.DESC, "time")); // TODO 페이지 사이즈 협의
        Page<BoardListRes> page = boardRepository.findBoardDto(pageRequest);

        return ApplicationResponse.ok(page);
    }

    @Transactional
    @Override
    public ApplicationResponse<Void> delBoard(Long id){
        Board board = boardRepository.findById(id).get();
        board.changeBoardDelete();
        return ApplicationResponse.ok();
    }

}
