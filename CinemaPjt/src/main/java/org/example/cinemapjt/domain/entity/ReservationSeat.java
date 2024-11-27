package org.example.cinemapjt.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "예매좌석")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationSeat {

    @EmbeddedId
    private ReservationSeatId id; // 복합키 객체
    /*
    ReservationSeatId에 겹치는 외부키 속성들 모두 정의
     */

    @Column(name = "예매일시")
    private Date reservationDate;

    @Column(name = "금액", length = 10, nullable = false)
    private String amount;
}
