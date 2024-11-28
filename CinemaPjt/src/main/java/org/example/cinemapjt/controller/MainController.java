package org.example.cinemapjt.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String showMainPage() { return "index"; }            // index.html 즉 메인홈페이지

    @GetMapping(value = "/admin")
    public String showAdminPage() { return "admin"; }           // admin.html 렌더링

    @GetMapping(value = "/signup")
    public String showSignupPage() { return "signup"; }         // signup.html 회원가입 페이지

    @GetMapping(value = "/movies")
    public String showMoviesPage() { return "movies"; }         // movies.html, 로그인 후 영화 선택 페이지
}
