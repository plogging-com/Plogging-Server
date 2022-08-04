package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
