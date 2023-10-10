package com.example.shop.entity;

import com.example.shop.constant.ItemSellStatus;
import com.example.shop.dto.ItemFormDto;
import com.example.shop.exception.OutOfStockException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "item")
@Builder

public class Item extends BaseEntity{

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm;      //상품 명

    @Column(name="price", nullable = false)
    private int price;          //가격

    @Column(nullable = false)
    private int stockNumber;    //재고량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    private String imgUrl;

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }
    public void removeStock(int stockNumber) {
        int restStock = this.stockNumber - stockNumber;
        if (restStock<0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. 현재 재고 수량:"+this.stockNumber+")");
        } this.stockNumber = restStock;
    }
    public void addStock(int stockNumber) { //상품의 재고를 증가시켜주는 메소드
        this.stockNumber+=stockNumber;
    }
}

