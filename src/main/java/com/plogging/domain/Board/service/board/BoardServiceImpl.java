package com.plogging.domain.Board.service.board;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.BoardCategory;
import com.plogging.domain.Board.entity.Category;
import com.plogging.domain.Board.exception.Board.NotEnoughCategory;
import com.plogging.domain.Board.repository.*;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final BoardCategoryRepository boardCategoryRepository;

    @Transactional
    @Override
    public ApplicationResponse<BoardRes> createBoard(createBoardReq createBoardReq) {
        // 카테고리 하나도 없을 경우 에러 (최소 1개 ~ 최대 3개)
        if(createBoardReq.getCategoryName1() == null) throw new NotEnoughCategory();

        String imageURL = "~~"; // s3Service.makeImage(questReq.getPhoto());

        User user = userRepository.findById(createBoardReq.getUserId()).orElseThrow(() -> new NotFoundUserException());
        Board board = boardRepository.save(createBoardReq.toEntityBoardWithPhoto(user));

        BoardRes boardRes = BoardRes.create(board);

        BoardCategory boardCategory1 = BoardCategory.builder()
                    .board(board)
                    .category(categoryRepository.findByName(createBoardReq.getCategoryName1()).get())
                    .build();
        boardCategoryRepository.save(boardCategory1);

        boardRes.addCategory(createBoardReq.getCategoryName1());

        if(createBoardReq.getCategoryName2() != null) {
            BoardCategory boardCategory2 = BoardCategory.builder()
                    .board(board)
                    .category(categoryRepository.findByName(createBoardReq.getCategoryName2()).get())
                    .build();
            boardCategoryRepository.save(boardCategory2);

            boardRes.addCategory(createBoardReq.getCategoryName2());
        }

        if(createBoardReq.getCategoryName3() != null) {
            BoardCategory boardCategory3 = BoardCategory.builder()
                    .board(board)
                    .category(categoryRepository.findByName(createBoardReq.getCategoryName3()).get())
                    .build();
            boardCategoryRepository.save(boardCategory3);

            boardRes.addCategory(createBoardReq.getCategoryName3());
        }

        return ApplicationResponse.create("created", boardRes);
    }

    @Override
    public ApplicationResponse<Page<BoardListRes>> getBoardList(Pageable pageable) {
//        Page<Board> boards = boardRepository.findAll(pageable);
//        Page<BoardListRes> result = boards.stream()
//                .map(b -> new BoardListRes(b))
//                .collect(toList());
        return ApplicationResponse.ok(boardRepository.findAll(pageable).map(BoardListRes::new));
    }

    @Transactional
    @Override
    public ApplicationResponse<Void> delBoard(Long id){
        Board board = boardRepository.findById(id).get();
        board.changeBoardDelete();
        return ApplicationResponse.ok();
    }

}
