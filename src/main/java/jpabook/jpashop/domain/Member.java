package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded// 내장타입을 포함했다.
    private Address address;

    @JsonIgnore//V1 엔티티 직접노출 JsonIgnore 넣으면 주문정보 빠짐
    @OneToMany(mappedBy = "member")//연관관계 주인이 아님, 읽기전용 , 값을 변경해도 변경안됨
    private List<Order> orders = new ArrayList<>();

}
