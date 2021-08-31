package com.chen.drools.service;

import com.chen.drools.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * 规则属性单元测试
 * <p>
 *
 * @Author LeifChen
 * @Date 2021-08-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AttributeTest {

    @Autowired
    private KieSession kieSession;

    @Test
    public void testNoLoop() {
        Person person = new Person();
        person.setName("no-loop");
        kieSession.insert(person);
        assertEquals(1, kieSession.fireAllRules());
    }

    @Test
    public void testLockOnActive() {
        Person person = new Person();
        person.setName("lock-on-active");
        kieSession.insert(person);
        assertEquals(1, kieSession.fireAllRules());
    }

    @Test
    public void testSalience() {
        Person person = new Person();
        person.setName("salience");
        kieSession.insert(person);
        assertEquals(2, kieSession.fireAllRules());
    }

    @Test
    public void testEnabled() {
        Person person = new Person();
        person.setName("enabled");
        kieSession.insert(person);
        assertEquals(1, kieSession.fireAllRules());
    }

    @Test
    public void testDateEffective() {
        Person person = new Person();
        person.setName("date-effective");
        kieSession.insert(person);
        assertEquals(1, kieSession.fireAllRules());
    }

    @Test
    public void testDateExpires() {
        Person person = new Person();
        person.setName("date-expires");
        kieSession.insert(person);
        assertEquals(1, kieSession.fireAllRules());
    }

    @Test
    public void testActivationGroup() {
        Person person = new Person();
        person.setName("activation-group");
        kieSession.insert(person);
        assertEquals(1, kieSession.fireAllRules());
    }

    @Test
    public void testAgendaGroup() {
        Person person = new Person();
        person.setName("agenda-group");
        kieSession.insert(person);
        kieSession.getAgenda().getAgendaGroup("ag1").setFocus();
        assertEquals(1, kieSession.fireAllRules());
    }

    @Test
    public void testAutoFocus() {
        Person person = new Person();
        person.setName("auto-focus");
        kieSession.insert(person);
        assertEquals(1, kieSession.fireAllRules());
    }
}