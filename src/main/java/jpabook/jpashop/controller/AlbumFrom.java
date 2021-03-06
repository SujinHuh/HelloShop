package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class AlbumFrom{

    private Long id;

    @NotEmpty(message = "이름입력은 필수 입니다.")
    private String name;
    private int price;
    private int stockQuantity;

    private String artist;
    private String etc;

}
