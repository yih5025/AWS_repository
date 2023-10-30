package Parsade.MediaParsade.repository;

import Parsade.MediaParsade.domain.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    void update(Long memberId, MemberUpdateDto updateParam);

    List<Member> findAll();




}
