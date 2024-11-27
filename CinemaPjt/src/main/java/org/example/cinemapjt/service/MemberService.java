package org.example.cinemapjt.service;

import org.example.cinemapjt.domain.dto.MemberDto;
import org.example.cinemapjt.domain.entity.Member;
import org.example.cinemapjt.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;                          // Repository 사용을 위해 DI 받아 사용

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();          // 암호화를 위해 사용

    // 새로운 회원 등록
    public void registerMember(MemberDto memberDto) {
        // 아이디 중복 확인
        if (memberRepository.findByMemberId(memberDto.getMemberId()) != null) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }

        // MemberDto -> Member 변환
        Member member = new Member();
        member.setMemberId(memberDto.getMemberId());
        member.setPassword(passwordEncoder.encode(memberDto.getPassword())); // 비밀번호 암호화
        member.setName(memberDto.getName());
        member.setPhoneNumber(memberDto.getPhoneNumber());
        member.setGrade(memberDto.getGrade() != null ? memberDto.getGrade() : "SILVER"); // 기본 등급 설정
        member.setCardNumber(memberDto.getCardNumber());

        // 데이터 저장
        memberRepository.save(member);
    }

    // 로그인 처리
    public Member login(String memberId, String password) {
        // 회원 조회
        Member member = memberRepository.findByMemberId(memberId);

        // 회원 존재 여부 확인
        if (member == null) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
        }

        return member; // 로그인 성공 시 Member 객체 반환
    }
}
