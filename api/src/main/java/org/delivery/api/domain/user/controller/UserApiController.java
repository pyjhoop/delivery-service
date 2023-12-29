package org.delivery.api.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me(
            @UserSession User user
            ){

        // 얘는 요청에 대한 응답이 갈때까지 글로벌하게 저장되는 저장공간이다.
        // 스코프 단위로 요청한번만 할거냐 아님 세션에서 데이터를 저장할거냐를 정할 수 있음.
//        var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
//        var userId = requestContext.getAttribute("userId", 0);

        var response = userBusiness.me(user.getId());
        return Api.OK(response);

    }

}
