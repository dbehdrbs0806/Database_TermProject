package org.example.cinemapjt.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScheduleId implements Serializable {

    @Column(name = "영화번호", length = 10, nullable = false)
    private String movieId;

    @Column(name = "상영시간", nullable = false)
    private Date showTime;

    @Column(name = "상영관번호", length = 10, nullable = false)
    private String theaterId;
}
