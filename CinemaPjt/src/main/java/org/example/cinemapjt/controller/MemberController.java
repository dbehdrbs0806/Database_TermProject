package org.example.cinemapjt.controller;

import org.example.cinemapjt.domain.dto.MemberDto;
import org.example.cinemapjt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody MemberDto memberDto) {
        try {
            memberService.registerMember(memberDto);
            return ResponseEntity.ok(Map.of("message", "회원가입이 완료되었습니다."));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginData) {
        String memberId = loginData.get("memberId");
        String password = loginData.get("password");

        // 관리자 계정 처리
        if ("admin".equals(memberId) && "1111".equals(password)) {
            return ResponseEntity.ok(Map.of("message", "관리자 로그인 성공", "role", "admin"));
        }

        try {
            memberService.login(memberId, password);
            return ResponseEntity.ok(Map.of("message", "로그인 성공")); // JSON 응답
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage())); // JSON 응답
        }
    }
}
