package org.example.cinemapjt.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "상영관좌석")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TheaterSeat {

    @EmbeddedId                                         // 외부키와 같은 복합키 사용을 지정
    private TheaterSeatId id; // 복합키 객체
    /* TheaterSeatId에서 필요한 속성을 모두 정의해놓음
       그래서 TheaterSeatId를 생성하면 모든 속성을 가짐
     */

}