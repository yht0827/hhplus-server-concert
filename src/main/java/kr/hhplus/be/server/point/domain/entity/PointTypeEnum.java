package kr.hhplus.be.server.point.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PointTypeEnum {
    CHARGE("충전"),
    USE("사용");

    private final String text;
}
