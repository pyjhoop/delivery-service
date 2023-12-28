package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeIfs{

    OK(200, 200, "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512, "Null point")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    // @Getter를 달게되면 어차피 동일한 메서드 명이 만들어지기에 굳이 오버라이드 할 필요없다.
//    @Override
//    public Integer getHttpStatusCode() {
//        return this.httpStatusCode;
//    }
//
//    @Override
//    public Integer getErrorCode() {
//        return this.errorCode;
//    }
//
//    @Override
//    public String getDescription() {
//        return this.description;
//    }
    // 해당 값들은 변경되면 안되기에 final로 초기화
}
