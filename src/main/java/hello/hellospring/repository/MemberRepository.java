package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);  // null발생시 optional로 감싸서 반환한다 많이 사용하는 방법
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
