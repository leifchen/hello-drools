package com.chen.drools.service;

import com.chen.drools.bean.Person;
import com.chen.drools.bean.School;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 条件关系单元测试
 * <p>
 *
 * @Author LeifChen
 * @Date 2021-08-31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConditionTest {

    @Autowired
    private KieBase kieBase;
    private KieSession kieSession;

    @Before
    public void before() {
        School school = new School();
        school.setClassNameList(Arrays.asList("一班", "二班", "三班"));
        Person person = new Person();
        person.setName("LeifChen");
        person.setClassName("一班");

        kieSession = kieBase.newKieSession();
        kieSession.insert(school);
        kieSession.insert(person);
    }

    @After
    public void after() {
        kieSession.dispose();
    }

    @Test
    public void testContains() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testContains")));
    }

    @Test
    public void testMemberOf() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testMemberOf")));
    }

    @Test
    public void testMatches() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testMatches")));
    }

    @Test
    public void testSoundslike() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testSoundslike")));
    }

    @Test
    public void testStr() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testStr")));
    }
}
