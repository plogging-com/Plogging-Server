package com.plogging.domain.Board.dto.board.request;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.BoardCategory;
import com.plogging.domain.User.entity.User;
import com.plogging.global.enumerations.PresenceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class createBoardReq {

    private String title;
    private String content;
    private MultipartFile photo;
    private Long user_idx;

    public Board toEntityWithPhoto(String photo, User user) {
        return new Board(user, this.title, this.content, LocalDateTime.now(), photo);
    }
}
