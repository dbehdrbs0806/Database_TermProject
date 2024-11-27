package org.example.cinemapjt.domain.dto;


import lombok.Data;

@Data
public class MemberDto {
    private String memberId;        // 회원 ID
    private String password;        // 비밀번호
    private String name;            // 회원 이름
    private String phoneNumber;     // 휴대전화
    private String grade;           // 등급 (예: SILVER, GOLD, VIP)
    private String cardNumber;      // 카드번호
}