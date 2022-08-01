package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    Inquiry inquiryCreate(Inquiry inquiry);
    Inquiry inquiryUpdate(Inquiry inquiry);
    void commentDeleteById(Long id);
}
