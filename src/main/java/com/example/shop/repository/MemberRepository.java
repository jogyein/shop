package com.example.shop.repository;

import com.example.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//public interface MemberRepository extends JpaRepository<Member, Long> {
//
//        @Query("SELECT m FROM Member m WHERE m.membername = :membername")
//        Member findByEmail(@Param("membername") String email);

        public interface MemberRepository extends JpaRepository<Member, Long> {

                @Query("SELECT u FROM Member u WHERE u.email = :email")
                Member findByEmail(@Param("email") String email);
}
