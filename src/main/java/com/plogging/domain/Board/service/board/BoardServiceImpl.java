package com.plogging.domain.Board.service.board;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.request.getAllBoardsByCategoryReq;
import com.plogging.domain.Board.dto.board.request.modifyBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardAllRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.entity.*;
import com.plogging.domain.Board.exception.Board.NotFoundBoardException;
import com.plogging.domain.Board.exception.Board.OverMaxContentLength;
import com.plogging.domain.Board.repository.BoardRepository;
import com.plogging.domain.Board.repository.PhotoRepository;
import com.plogging.domain.Board.service.Category.CategoryService;
import com.plogging.domain.Board.service.inquiry.InquiryService;
import com.plogging.domain.User.BadgeList;
import com.plogging.domain.Board.exception.Board.NotEnoughCategory;
import com.plogging.domain.Board.repository.*;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.domain.User.service.userBadge.UserBadgeService;
import com.plogging.global.dto.ApplicationResponse;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final InquiryService inquiryService;
    private final CategoryService categoryService;
    private final AwsS3Service awsS3Service;

    private final UserBadgeService userBadgeService;
    private final CategoryRepository categoryRepository;
    private final BoardCategoryRepository boardCategoryRepository;

    @Transactional
    @Override
    public ApplicationResponse<BoardRes> createBoard(createBoardReq createBoardReq) {
        // 글자수가 700자를 넘는 경우 에러
        if(createBoardReq.getContent().length() > 700) throw new OverMaxContentLength();
        // 카테고리 하나도 없을 경우 에러 (최소 1개 ~ 최대 3개)
        if(createBoardReq.getCategoryName1() == null) throw new NotEnoughCategory();

        User user = userRepository.findById(createBoardReq.getUserId()).orElseThrow(() -> new NotFoundUserException());
        Board board = boardRepository.save(createBoardReq.toEntityBoard(user));






        boolean isFirstBoard = boardRepository.findByUser(user).get().isEmpty();
        if(isFirstBoard) userBadgeService.giveBadgeToUser(BadgeList.NewBiePhotoGrapher, user);

        // photo 생성
        List<String> filenames = awsS3Service.uploadImages(createBoardReq.getPhotos());
        for(String i : filenames){
            Photo photo = Photo.builder()
                    .fileName(i)
                    .board(board)
                    .build();

            photoRepository.save(photo);
        }

        List<Photo> photos = photoRepository.findAllByBoardId(board.getId());
        if(photos != null) board.addMainPhotoUrl(awsS3Service.makeUrlOfFilename((photos.get(0).getFileName())));

        List<String> urls = new ArrayList<>();
        for (Photo i : photos){
            urls.add(awsS3Service.makeUrlOfFilename(i.getFileName()));
        }

        BoardRes boardRes = BoardRes.create(board , isFirstBoard, urls);

        // 카테고리 연관관계 및 생성
        BoardCategory boardCategory1 = BoardCategory.builder()
                    .board(board)
                    .category(categoryRepository.findByName(createBoardReq.getCategoryName1()).get())
                    .build();
        boardCategoryRepository.save(boardCategory1);

        boardRes.addCategory(createBoardReq.getCategoryName1());

        if(createBoardReq.getCategoryName2() != null && categoryRepository.findByName(createBoardReq.getCategoryName2()).isPresent()) {
            BoardCategory boardCategory2 = BoardCategory.builder()
                    .board(board)
                    .category(categoryRepository.findByName(createBoardReq.getCategoryName2()).get())
                    .build();
            boardCategoryRepository.save(boardCategory2);

            boardRes.addCategory(createBoardReq.getCategoryName2());
        }

        if(createBoardReq.getCategoryName3() != null && categoryRepository.findByName(createBoardReq.getCategoryName3()).isPresent()) {
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
    public ApplicationResponse<Page<BoardAllRes>> getAllBoardsByCategory(Pageable pageable, getAllBoardsByCategoryReq getAllBoardsByCategoryReq){
        if(getAllBoardsByCategoryReq.getCategoryName() == null) return ApplicationResponse.ok(boardRepository.findAll(pageable).map(BoardAllRes::create));
        else return ApplicationResponse.ok(boardRepository.findAllByCategoryIn(pageable, getAllBoardsByCategoryReq.getCategoryName()).map(BoardAllRes::create));
    }

    @Transactional // 조회용이긴 하지만, 조회 기록을 남기기 위해 조회 엔티티(inquiry)룰 생성해야 하므로 붙여줘야 함
    @Override
    public ApplicationResponse<BoardRes> getBoard(Long boardId){
        if(!boardRepository.existsById(boardId)) throw new NotFoundBoardException();
        Board board = boardRepository.findById(boardId).get();

        // 조회 엔티티(inquiry) 생성
        inquiryService.createInquiry(boardId);

        List<Photo> photos = photoRepository.findAllByBoardId(board.getId());
        List<String> urls = new ArrayList<>();
        for (Photo i : photos){
            urls.add(awsS3Service.makeUrlOfFilename(i.getFileName()));
        }
        return ApplicationResponse.ok(BoardRes.create(board , true, urls));
}

    @Transactional
    @Override
    public ApplicationResponse<Void> delBoard(Long id){
        if(!boardRepository.existsById(id)) throw new NotFoundBoardException();
        Board board = boardRepository.findById(id).get();
        board.changeBoardDelete();

        return ApplicationResponse.ok();
    }

    @Transactional
    @Override
    public ApplicationResponse<BoardRes> modifyBoard(modifyBoardReq modifyBoardReq){
        if(!boardRepository.existsById(modifyBoardReq.getBoardId())) throw new NotFoundBoardException();
        Board board = boardRepository.findById(modifyBoardReq.getBoardId()).get();

        board.modifyBoard(modifyBoardReq.getTitle(), modifyBoardReq.getContent());

        List<String> urls = new ArrayList<>();
        if(modifyBoardReq.getPhotos() != null){
            // 기존 사진 모두 삭제
            List<Photo> oldPhotos = photoRepository.findAllByBoardId(board.getId());
            for (Photo i : oldPhotos){
                photoRepository.delete(i);
            }

            // photo 생성
            List<String> filenames = awsS3Service.uploadImages(modifyBoardReq.getPhotos());
            for(String i : filenames){
                Photo photo = Photo.builder()
                        .fileName(i)
                        .board(board)
                        .build();

                photoRepository.save(photo);
            }

            List<Photo> photos = photoRepository.findAllByBoardId(board.getId());
            board.addMainPhotoUrl(awsS3Service.makeUrlOfFilename((photos.get(0).getFileName())));

            for (Photo i : photos){
                urls.add(awsS3Service.makeUrlOfFilename(i.getFileName()));
            }
        }

        BoardRes boardRes = BoardRes.create(board , false, urls);

        // 카테고리 수정할게 하나라도 있는 경우
        if(modifyBoardReq.getCategoryName1() != null || modifyBoardReq.getCategoryName2() != null || modifyBoardReq.getCategoryName3() != null){
            // 기존 카테고리 모두 삭제
            List<BoardCategory> boardCategories = boardCategoryRepository.findAllByBoardId(board.getId());
            for(BoardCategory i : boardCategories){
                boardCategoryRepository.delete(i);
            }

            if(modifyBoardReq.getCategoryName1() != null) {
                BoardCategory boardCategory1 = BoardCategory.builder()
                        .board(board)
                        .category(categoryRepository.findByName(modifyBoardReq.getCategoryName1()).get())
                        .build();
                boardCategoryRepository.save(boardCategory1);

                boardRes.addCategory(modifyBoardReq.getCategoryName1());
            }

            if(modifyBoardReq.getCategoryName2() != null) {
                BoardCategory boardCategory2 = BoardCategory.builder()
                        .board(board)
                        .category(categoryRepository.findByName(modifyBoardReq.getCategoryName2()).get())
                        .build();
                boardCategoryRepository.save(boardCategory2);

                boardRes.addCategory(modifyBoardReq.getCategoryName2());
            }

            if(modifyBoardReq.getCategoryName3() != null) {
                BoardCategory boardCategory3 = BoardCategory.builder()
                        .board(board)
                        .category(categoryRepository.findByName(modifyBoardReq.getCategoryName3()).get())
                        .build();
                boardCategoryRepository.save(boardCategory3);

                boardRes.addCategory(modifyBoardReq.getCategoryName3());
            }
        }

        return ApplicationResponse.ok(boardRes);
    }
}
