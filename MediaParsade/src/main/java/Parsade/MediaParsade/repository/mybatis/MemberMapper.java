package Parsade.MediaParsade.repository.mybatis;


import Parsade.MediaParsade.domain.Member;
import Parsade.MediaParsade.repository.MemberUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    void save(Member member);

    void update(@Param("id") Long id, @Param("updateParam")
    MemberUpdateDto updateParam);

    List<Member> findAll();

}
