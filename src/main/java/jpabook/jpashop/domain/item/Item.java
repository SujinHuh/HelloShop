package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속관맵핑 부무테이블에 맵핑 해줘야 함,싱글테이블 전략
@DiscriminatorColumn(name = "dtype")
@Getter @Setter

public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    //상속광계 맵핑

    private String name;

    private int price;

    private int stockQuantity;

}
