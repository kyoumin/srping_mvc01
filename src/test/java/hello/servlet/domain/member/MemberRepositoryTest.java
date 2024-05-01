package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;

//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;


class MemberRepositoryTest {

    /* 싱글톤이라 new 로 하면안됨, 스프링 자체가 싱글톤을 보장해줘서 스프링은 싱글톤 신경쓸필요없음*/
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Member member = new Member("hello",28);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("hello1",27);
        Member member2 = new Member("hello2",28);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        ArrayList<Member> list = (ArrayList<Member>) memberRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);
        assertThat(list).contains(member1,member2);
    }

}
