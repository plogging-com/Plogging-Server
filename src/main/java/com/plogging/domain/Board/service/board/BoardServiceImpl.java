package com.plogging.domain.Board.service.board;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.request.getAllBoardsByCategoryReq;
import com.plogging.domain.Board.dto.board.response.BoardAllRes;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public ApplicationResponse<Page<BoardAllRes>> getAllBoards(Pageable pageable) {
        return ApplicationResponse.ok(boardRepository.findAll(pageable).map(BoardAllRes::create));
    }

    @Override
    public ApplicationResponse<Page<BoardAllRes>> getAllBoardsBy1Category(Pageable pageable, getAllBoardsByCategoryReq getAllBoardsByCategoryReq){
        Category category = categoryRepository.findByName(getAllBoardsByCategoryReq.getCategoryName1()).get();
        return ApplicationResponse.ok(boardCategoryRepository.findByCategory(pageable, category).map(BoardAllRes::create2));
    }

    @Override
    public ApplicationResponse<Page<BoardAllRes>> getAllBoardsBy2Category(Pageable pageable, getAllBoardsByCategoryReq getAllBoardsByCategoryReq){
        Category category1 = categoryRepository.findByName(getAllBoardsByCategoryReq.getCategoryName1()).get();
        Category category2 = categoryRepository.findByName(getAllBoardsByCategoryReq.getCategoryName2()).get();
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        return ApplicationResponse.ok(boardCategoryRepository.findAllByCategoryIn(pageable, categories).map(BoardAllRes::create2));
    }

    @Override
    public ApplicationResponse<Page<BoardAllRes>> getAllBoardsBy3Category(Pageable pageable, getAllBoardsByCategoryReq getAllBoardsByCategoryReq){
        Category category1 = categoryRepository.findByName(getAllBoardsByCategoryReq.getCategoryName1()).get();
        Category category2 = categoryRepository.findByName(getAllBoardsByCategoryReq.getCategoryName2()).get();
        Category category3 = categoryRepository.findByName(getAllBoardsByCategoryReq.getCategoryName3()).get();
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        return ApplicationResponse.ok(boardCategoryRepository.findAllByCategoryIn(pageable, categories).map(BoardAllRes::create2));
    }

    @Transactional
    @Override
    public ApplicationResponse<Void> delBoard(Long id){
        Board board = boardRepository.findById(id).get();
        board.changeBoardDelete();

        return ApplicationResponse.ok();
    }

}
