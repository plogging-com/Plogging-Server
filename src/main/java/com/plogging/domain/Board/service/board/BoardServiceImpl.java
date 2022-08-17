package com.plogging.domain.Board.service.board;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.BoardCategory;
import com.plogging.domain.Board.entity.Category;
import com.plogging.domain.Board.repository.*;
import com.plogging.domain.User.entity.User;
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
        String imageURL = "~~"; // s3Service.makeImage(questReq.getPhoto());

        User user = userRepository.findById(createBoardReq.getUser_idx()).get();
        Board board = boardRepository.save(createBoardReq.toEntityBoardWithPhoto(imageURL, user));

        BoardRes boardRes = BoardRes.create(board);

        if(createBoardReq.getCategoryName1() != null) {
            BoardCategory boardCategory = BoardCategory.builder()
                    .board(board)
                    .category(categoryRepository.save(createBoardReq.toEntityCategory(createBoardReq.getCategoryName1())))
                    .build();

            boardRes.addCategory(createBoardReq.getCategoryName1());
        }

        if(createBoardReq.getCategoryName2() != null) {
            BoardCategory boardCategory = BoardCategory.builder()
                    .board(board)
                    .category(categoryRepository.save(createBoardReq.toEntityCategory(createBoardReq.getCategoryName2())))
                    .build();

            boardRes.addCategory(createBoardReq.getCategoryName2());
        }

        if(createBoardReq.getCategoryName3() != null) {
            BoardCategory boardCategory = BoardCategory.builder()
                    .board(board)
                    .category(categoryRepository.save(createBoardReq.toEntityCategory(createBoardReq.getCategoryName3())))
                    .build();

            boardRes.addCategory(createBoardReq.getCategoryName3());
        }

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
