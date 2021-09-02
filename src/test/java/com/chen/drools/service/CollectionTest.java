package com.chen.drools.service;

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
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * 集合单元测试
 * <p>
 *
 * @Author LeifChen
 * @Date 2021-09-01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionTest {

    @Autowired
    private KieBase kieBase;
    private KieSession kieSession;

    @Before
    public void before() {
        School school = new School();
        school.setClassNameList(Arrays.asList("一班", "二班", "三班"));
        school.setClassNameSet(new HashSet<String>() {{
            add("一班");
            add("二班");
            add("三班");
        }});
        school.setClassNameMap(new HashMap<String, Integer>() {{
            put("一班", 1);
            put("二班", 2);
            put("三班", 3);
        }});

        kieSession = kieBase.newKieSession();
        kieSession.insert(school);
        kieSession.insert(Arrays.asList("一班", "二班", "三班"));
        kieSession.insert(new HashSet<String>() {{
            add("一班");
            add("二班");
            add("三班");
        }});
        kieSession.insert(new HashMap<String, Integer>() {{
            put("一班", 1);
            put("二班", 2);
            put("三班", 3);
        }});
    }

    @After
    public void after() {
        kieSession.dispose();
    }

    @Test
    public void testList() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testList")));
    }

    @Test
    public void testSet() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testSet")));
    }

    @Test
    public void testMap() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testMap")));
    }

    @Test
    public void testCollectionList() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testCollectionList")));
    }

    @Test
    public void testCollectionSet() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testCollectionSet")));
    }

    @Test
    public void testCollectionMap() {
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("testCollectionMap")));
    }
}
