package org.delivery.db.account;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder // 이렇게해야지 부모 클래스의 값도 같이 빌더를 사용할 수 있음. @Builder는 id가 안뜸.
@Data
@EqualsAndHashCode(callSuper = true) //이퀄즈와 해쉬코드 비교시 부모 클래스의 값도 같이 비교할건지 아닌지에 대한 것. true면 부모의 값도 같이 하겠다.
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {

    public AccountEntity() {

    }
}
