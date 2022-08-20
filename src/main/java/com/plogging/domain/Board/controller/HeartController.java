package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createHeartReq;
import com.plogging.domain.Board.dto.board.request.delHeartReq;
import com.plogging.domain.Board.service.board.BoardServiceImpl;
import com.plogging.domain.Board.service.heart.HeartService;
import com.plogging.domain.Board.service.heart.HeartServiceImpl;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags = "Heart API")
@RequestMapping("api/heart")
public class HeartController {
    private final HeartService heartService;

    /**
     * 좋아요
     * @author 강신현
     */
    @ApiOperation(value = "좋아요", notes = "")
    @PostMapping("")
    public ApplicationResponse<Void> createHeart(@ModelAttribute createHeartReq createHeartReq){
        return heartService.createHeart(createHeartReq);
    }

    /**
     * 좋아요 취소
     * @author 강신현
     */
    @ApiOperation(value = "좋아요 취소", notes = "")
    @DeleteMapping("/del")
    public ApplicationResponse<Void> delHeart(@ModelAttribute delHeartReq delHeartReq){
        return heartService.delHeart(delHeartReq);
    }

}
