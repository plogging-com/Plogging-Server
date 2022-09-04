package com.plogging.domain.User.repository;

import com.plogging.domain.User.dto.request.UserFindReq;
import com.plogging.domain.User.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{


    Optional<User> findByLoginId(String loginId);

    Optional<String> findByNickName(String nickName);

    Page<User> findByNickNameContaining(String nickName, Pageable pageable);

    public default List<User> findUserName(UserFindReq userFindReq) {
        Page<User> page = this.findByNickNameContaining(userFindReq.getUserNickName(), PageRequest.of(userFindReq.getPg() - 1, userFindReq.getSz(),
                Sort.Direction.DESC, "id"));
        return page.getContent();
    }

    Optional<User> findByPhone(String phone);

    Optional<User> findByLoginIdAndPhone(String loginId, String phone);


}
