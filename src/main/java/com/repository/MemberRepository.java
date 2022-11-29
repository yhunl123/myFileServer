package com.repository;

import com.VO.MemberVO;
import com.table.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Map;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(
        value = "SELECT m.memberId as memberId, " +
                "m.memberName as memberName, " +
                "m.userToken as userToken, " +
                "m.memberAuth as memberAuth " +
                "FROM Member m " +
                "WHERE m.memberName = :#{#member.memberName} " +
                "AND m.memberPw = :#{#member.memberPw}"
    )
    Map<String, Object> findByMemberName(@Param(value = "member") MemberVO memberVO);

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE Member m " +
                "SET m.userToken = :userToken " +
                "WHERE m.memberId = :memberId"
    )
    void updateToken(@Param(value = "userToken") String userToken, @Param(value = "memberId") Integer memberId);


}
