package com.example.jpastudy.member;

import com.example.jpastudy.member.domain.MemberRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/view/member")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/list")
    public String list(Map<String, Object> model) {

        model.put("members", memberRepository.findAll());
        return "/member/list";
    }
}
