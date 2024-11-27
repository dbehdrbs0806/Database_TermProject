package org.example.cinemapjt.domain.repository;


import org.example.cinemapjt.domain.entity.ReservationSeat;
import org.example.cinemapjt.domain.entity.ReservationSeatId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationSeatRepository extends JpaRepository<ReservationSeat, ReservationSeatId> {

    List<ReservationSeat> findByIdMovieId(String movieId); // 특정 영화의 예매된 좌석 조회
}

