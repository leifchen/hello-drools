package com.chen.drools.service;

import com.chen.drools.bean.Person;
import com.chen.drools.bean.School;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 中级语法单元测试
 * <p>
 *
 * @Author LeifChen
 * @Date 2021-09-02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AdvanceTest {

    @Autowired
    private KieBase kieBase;
    private KieSession kieSession;

    @Before
    public void before() {
        kieSession = kieBase.newKieSession();
    }

    @After
    public void after() {
        kieSession.dispose();
    }

    @Test
    public void testFunction() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testFunction")));
    }

    @Test
    public void testQuery() {
        kieSession.insert(Person.builder().name("LeifChen").age(18).build());
        kieSession.insert(Person.builder().name("Lucy").age(16).build());
        kieSession.insert(Person.builder().name("Lily").age(20).build());
        Object[] objects = new Object[]{"LeifChen"};
        QueryResults results = kieSession.getQueryResults("person age is 18 and name is LeifChen", objects);
        for (QueryResultsRow result : results) {
            Person person = (Person)result.get("person");
            log.info("符合查询条件的对象：{}", person);
        }
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testQuery")));
        assertEquals(1, results.size());
    }

    @Test
    public void testDeclare() {
        assertEquals(2, kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("testDeclare")));
    }

    @Test
    public void testGlobal() {
        // 原始global全局变量
        kieSession.setGlobal("count", 10);
        School school = new School();
        school.setCode("S0");
        kieSession.setGlobal("school", school);
        kieSession.setGlobal("list", new ArrayList<>());

        assertEquals(2, kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("testGlobal")));

        // 执行规则后的global全局变量
        int updateCount = (int)kieSession.getGlobal("count");
        School updateSchool = (School)kieSession.getGlobal("school");
        List updateList = (List)kieSession.getGlobal("list");

        assertEquals(10, updateCount);
        assertEquals("S1", updateSchool.getCode());
        assertEquals(2, updateList.size());
    }
}
