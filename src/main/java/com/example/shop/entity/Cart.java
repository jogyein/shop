package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "cart")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity{

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)  //회원 엔티티와 일대일로 매핑
    @JoinColumn(name = "member_id")
    //매핑할 외래키를 지정 name을 명시안하면 알아서 찾지만
    //원하는대로 생성안될수도 있음
    private Member member;

    public static Cart createCart(Member member){
        Cart cart = new Cart();
        cart.setMember(member);
        return cart;
    }
}
