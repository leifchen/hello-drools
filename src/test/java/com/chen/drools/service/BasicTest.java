package com.chen.drools.service;

import com.chen.drools.bean.Person;
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

import static org.junit.Assert.assertEquals;

/**
 * 基础语法单元测试
 * <p>
 *
 * @Author LeifChen
 * @Date 2021-09-01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicTest {

    @Autowired
    private KieBase kieBase;
    private KieSession kieSession;

    @Before
    public void before() {
        Person person = new Person();
        person.setAge(18);

        kieSession = kieBase.newKieSession();
        kieSession.insert(person);
    }

    @After
    public void after() {
        kieSession.dispose();
    }

    @Test
    public void testAdd() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testAdd")));
    }

    @Test
    public void testSub() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testSub")));
    }

    @Test
    public void testMul() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testMul")));
    }

    @Test
    public void testDiv() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testDiv")));
    }

    @Test
    public void testMod() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testMod")));
    }
}
