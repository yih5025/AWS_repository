package Parsade.MediaParsade.service;

import Parsade.MediaParsade.domain.Member;
import Parsade.MediaParsade.repository.MemberUpdateDto;

import java.util.List;

public interface MemberService {

    Member save(Member member);
    void update(Long memberId, MemberUpdateDto updateParam);

    List<Member> findAll();

}
