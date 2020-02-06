package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("B")//싱글테이블 저장할 때 구분할 수 있는게 필요함 (디비)
@Getter @Setter
public class Book extends Item {

private String author;

private String isbn;
}
