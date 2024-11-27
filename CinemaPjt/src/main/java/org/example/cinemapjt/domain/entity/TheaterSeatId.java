package org.example.cinemapjt.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/* 외부키로 사용하기 위한 테이블의 구성
   즉 외부키로 겹쳐 사용되는 속성들이 담겨져있는 객체파일
* */
@Embeddable                                 // 복합키로 사용되는 테이블
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TheaterSeatId implements Serializable {

    @Column(name = "상영관번호", length = 10, nullable = false)
    private String theaterId;

    @Column(name = "열번호", length = 5, nullable = false)
    private String rowNumber;

    @Column(name = "위치번호", length = 5, nullable = false)
    private String seatNumber;

    // 모두 fk로 사용됨
}
