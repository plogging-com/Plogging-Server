package com.plogging.domain.Board.service.board;

import com.plogging.domain.Board.dto.board.request.createBoardReq;
import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.dto.board.response.BoardRes;
import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Photo;
import com.plogging.domain.Board.exception.Board.NotFoundBoardException;
import com.plogging.domain.Board.exception.Board.OverMaxContentLength;
import com.plogging.domain.Board.repository.BoardRepository;
import com.plogging.domain.Board.repository.PhotoRepository;
import com.plogging.domain.Board.service.inquiry.InquiryService;
import com.plogging.domain.User.BadgeList;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final InquiryService inquiryService;
    private final AwsS3Service awsS3Service;

    private final UserBadgeService userBadgeService;

    @Transactional
    @Override
    public ApplicationResponse<BoardRes> createBoard(createBoardReq createBoardReq) {
        // 글자수가 700자를 넘는 경우 에러
        if(createBoardReq.getContent().length() > 700) throw new OverMaxContentLength();

        User user = userRepository.findById(createBoardReq.getUserId()).orElseThrow(() -> new NotFoundUserException());

        boolean isFirstBoard = boardRepository.findByUser(user).get().isEmpty();
        if(isFirstBoard) userBadgeService.giveBadgeToUser(BadgeList.NewBiePhotoGrapher, user);

        Board board = boardRepository.save(createBoardReq.toEntityBoard(user));

        // photo 여러개
        List<String> filenames = awsS3Service.uploadImages(createBoardReq.getPhotos());
        for(String i : filenames){
            Photo photo = Photo.builder()
                    .url(AwsS3Service.makeUrlOfFilename(i))
                    .board(board)
                    .build();

            photoRepository.save(photo);
        }

        List<Photo> photos = photoRepository.findAllByBoardId(board.getId());
        if(photos != null) board.addMainPhotoUrl(photos.get(0).getUrl());
        BoardRes boardRes = BoardRes.create(board , isFirstBoard, photos);
        return ApplicationResponse.create("created", boardRes);
}

    @Override
    public ApplicationResponse<Page<BoardListRes>> getBoardList(Pageable pageable) {
        return ApplicationResponse.ok(boardRepository.findAll(pageable).map(BoardListRes::create));
    }

    @Transactional // 조회용이긴 하지만, 조회 기록을 남기기 위해 조회 엔티티(inquiry)룰 생성해야 하므로 붙여줘야 함
    @Override
    public ApplicationResponse<BoardRes> getBoard(Long boardId){
        if(!boardRepository.existsById(boardId)) throw new NotFoundBoardException();
        Board board = boardRepository.findById(boardId).get();

        // 조회 엔티티(inquiry) 생성
        inquiryService.createInquiry(boardId);

        List<Photo> photos = photoRepository.findAllByBoardId(board.getId());
        return ApplicationResponse.ok(BoardRes.create(board , true, photos));
}

    @Transactional
    @Override
    public ApplicationResponse<Void> delBoard(Long id){
        Board board = boardRepository.findById(id).get();
        board.changeBoardDelete();
        return ApplicationResponse.ok();
    }

}
