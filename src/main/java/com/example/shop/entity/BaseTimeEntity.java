package com.example.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class}) //Auditing을 적용하기위해
@MappedSuperclass //공통 매핑정보가 필요할 떄 사용 자식클래스에게 매핑 정보만 제공
@Data
public class BaseTimeEntity {
    @CreatedDate //저장될 때 시간을 자동으로 저장
    @Column(updatable = false)
    private LocalDateTime regTime;

    @LastModifiedDate //값을 변경할 때 시간을 자동으로 저장
    private LocalDateTime updateTime;

}
