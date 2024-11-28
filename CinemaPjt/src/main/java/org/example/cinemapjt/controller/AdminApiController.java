package org.example.cinemapjt.controller;

import org.example.cinemapjt.domain.dto.DiscountDto;
import org.example.cinemapjt.domain.dto.MemberDto;
import org.example.cinemapjt.service.GradeService;
import org.example.cinemapjt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// 사용자, 입력 혹은 가져오는 출력으로 부터 데이터를 조회(get), 수정(post)를 할 때 Controller에 코드를 작성
// 관리자에서 회원의 정보를 긁어 오고 업데이트 하기위한 Controller
@RestController
@RequestMapping("/admin/api")
public class AdminApiController {

    // 의존성 주입 DI를 위한 객체 생성과 Autowired
    private final MemberService memberService;
    private final GradeService gradeService;

    // 생성자 DI
    @Autowired
    public AdminApiController(MemberService memberService, GradeService gradeService) {
        this.memberService = memberService;
        this.gradeService = gradeService;
    }

    /* /users 형식의 rest api에 회원의 정보를 가져와서 보냄
       memberService의 회원의 데이터를 모두 가져오는 getAllMembers() 사용
       List에 member(회원) 내용을 담음 <MemberDto> 사용
     */
    @GetMapping("/users")
    public ResponseEntity<List<MemberDto>> getAllUsers() {
        List<MemberDto> members = memberService.getAllMembers();                // 모든 회원의 정보를
        return ResponseEntity.ok(members);
        /* ResponseEntity: Spring에서 HTTP 응답의 표현을 사용할 수 있는 클래스
            응답의 코드 200, 404 등을 명시적으로 설정 가능
            또한 데이터를 Http의 body에 담아 사용할 수 있음
         */
    }

    // /users/update api형식에 회원의 정보의 업데이트를 반영하기 위한 함수
    @PostMapping("/users/update")
    public ResponseEntity<String> updateUserStatus(@RequestBody MemberDto memberDto) {
        try {
            memberService.updateMemberStatus(memberDto);
            if (memberDto.getGrade() != null) {

                return ResponseEntity.ok("회원 등급이 성공적으로 업데이트되었습니다.");
            }
            return ResponseEntity.ok("회원 정보가 성공적으로 업데이트되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 할인율 관련

    // 모든 할인율 조회
    @GetMapping("/discounts")
    public ResponseEntity<List<DiscountDto>> getAllDiscounts() {
        try {
            List<DiscountDto> discounts = gradeService.getAllDiscounts();
            return ResponseEntity.ok(discounts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 특정 등급의 할인율 조회
    @GetMapping("/discounts/{grade}")
    public ResponseEntity<DiscountDto> getDiscountByGrade(@PathVariable String grade) {
        try {
            DiscountDto discount = gradeService.getDiscountByGrade(grade);
            return ResponseEntity.ok(discount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 할인율 업데이트
    @PostMapping("/discounts/update")
    public ResponseEntity<String> updateDiscounts(@RequestBody Map<String, Double> discountUpdates) {
        try {
            gradeService.updateDiscounts(discountUpdates);
            return ResponseEntity.ok("할인율이 성공적으로 업데이트되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류로 인해 할인율 업데이트에 실패했습니다.");
        }
    }


}
