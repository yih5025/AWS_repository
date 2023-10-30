package Parsade.MediaParsade.controller;


import Parsade.MediaParsade.domain.Member;
import Parsade.MediaParsade.form.DisplayForm;
import Parsade.MediaParsade.repository.MemberUpdateDto;
import Parsade.MediaParsade.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final MemberService memberService;



    // 리액트에서 JSON 객체로 로그인 정보를 넘겨주면 멤버저장소에 저장 -> MyBatis를 통한 MYSQL 연결로 저장 확인

    @ResponseBody
    @PostMapping("/api/login")
    public String login(@RequestBody Member member, HttpServletRequest request){
        memberService.save(member);
        log.info("name={}, studentId={}",member.getName(),member.getStudentId());

        // 추후 사용자 타입과 메세지, 효과가 들어올 때, 사용자를 구분하기 위해 세션을 붙여서 전송 -> 프론트엔드 파트와 통합 혹은 이야기 해봐야됨
        HttpSession session = request.getSession();
        session.setAttribute("사용자 정보", member);

        return "OK";
    }


    // 로그인 사용자 정보 반환:

    @ResponseBody
    @GetMapping("/api/guest")
    public List<Member> login(){
        log.info("요청받음");
        return memberService.findAll();
    }


    @ResponseBody
    @PostMapping("/api/common")
    public DisplayForm commons(@RequestBody Member member, HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if (session != null) {
            // 세션에서 사용자 정보를 가져옵니다.
            Member originalMember = (Member) session.getAttribute("사용자 정보");
            log.info("name={}, studentId={}",originalMember.getName(), originalMember.getStudentId());
            if (originalMember.getStudentId() != null) {
                DisplayForm displayForm = new DisplayForm(member.getType(), member.getText(), member.getSelection().toString());

                Long id = originalMember.getId();
                MemberUpdateDto dto = new MemberUpdateDto(member.getType(),member.getText(),
                        member.getSelection().toString());
                log.info("id={}, dto={}, member={}", id, dto, member);

                // 업데이트된 정보를 저장합니다.
                memberService.update(id, dto);
                return displayForm;
            }

            log.info("not loginInfo");
            return null;
        }
        log.info("not session");
        return null;

    }


    // 함수 사용자 저장 정보
    // 세션정보를 통해서 기존 로그인 정보를 가져와서 해당 열에 데이터베이스 정보를 업데이트 한다.
    @ResponseBody
    @PostMapping("/api/function")
    public Member functions(@RequestBody Member member, HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if (session != null) {
            // 세션에서 사용자 정보를 가져옵니다.
            Member originalMember = (Member) session.getAttribute("사용자 정보");
            log.info("name={}, studentId={}",originalMember.getName(), originalMember.getStudentId());
            if (originalMember.getStudentId() != null) {
                Long id = originalMember.getId();
                MemberUpdateDto dto = new MemberUpdateDto(member.getType(),member.getText(),
                        member.getSelection().toString());
                log.info("id={}, dto={}, member={}", id, dto, member);

                // 업데이트된 정보를 저장합니다.
                memberService.update(id, dto);
                return member;
            }

            log.info("not loginInfo");
            return null;
        }
        log.info("not session");
        return null;
    }

}
