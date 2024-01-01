package org.delivery.db.userorder;

import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {

    // 특정 유저의 모든 주문을 조회
    // select * from user_order where userId = ? and status = ? order by id desc
    List<UserOrderEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);

    // 특정 상태를 갖는 유저의 모든 주문 조회
    // select * from user_order where userId = ? and status in(?,?,...) order by id desc;
    List<UserOrderEntity> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> status);

    //특정 주문
    // select * from user_order where id = ? and status = ? and userId = ?
    Optional<UserOrderEntity> findAllByIdAndStatusAndUserId(Long id, UserOrderStatus status, Long userId);

    // select * from user_order where id = ? and userId = ?
    Optional<UserOrderEntity> findAllByIdAndUserId(Long id, Long userId);


}
