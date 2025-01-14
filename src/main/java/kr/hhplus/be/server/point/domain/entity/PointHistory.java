package kr.hhplus.be.server.point.domain.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "point_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_history_id")
    private Long pointHistoryId;

    @Column(name = "point_id")
    private Long pointId;

    @Column
    @Enumerated(EnumType.STRING)
    private PointTypeEnum type;

    @Column
    private Integer amount;

    @Column
    private String comment;

    @Builder
    public PointHistory(Long pointHistoryId, Long pointId, PointTypeEnum type, Integer amount, String comment) {
        this.pointHistoryId = pointHistoryId;
        this.pointId = pointId;
        this.type = type;
        this.amount = amount;
        this.comment = comment;
    }
}
