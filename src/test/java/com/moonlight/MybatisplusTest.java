package com.moonlight;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moonlight.mybatisplus.entity.Member;
import com.moonlight.mybatisplus.mapper.MemberMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));

        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        Member member = memberMapper.selectOne(queryWrapper);
        Assert.assertEquals("邰景磊", member.getmNickname());
        System.out.println(member.getmNickname());
    }
}
