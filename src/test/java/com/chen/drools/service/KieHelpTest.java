package com.chen.drools.service;

import com.chen.drools.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.template.ObjectDataCompiler;
import org.junit.Test;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * KieHelper单元测试
 * <p>
 *
 * @Author LeifChen
 * @Date 2021-09-18
 */
@Slf4j
public class KieHelpTest {

    /**
     * 测试拼接字符串规则文件
     */
    @Test
    public void testStr() {
        String rule = "package rules\n" +
                "\n" +
                "import com.chen.drools.bean.Person\n" +
                "\n" +
                "rule \"testStr\"\n" +
                "    when\n" +
                "        $p:Person(age==18)\n" +
                "    then\n" +
                "        System.out.println(\"匹配年龄为18的人:\" + $p);\n" +
                "        $p.setAge(20);\n" +
                "        update($p);\n" +
                "end";
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(rule, ResourceType.DRL);
        KieSession kieSession = kieHelper.build().newKieSession();

        Person person = Person.builder().name("LeifChen").age(18).build();
        kieSession.insert(person);
        assertEquals(1, kieSession.fireAllRules());
        kieSession.dispose();
    }

    /**
     * 测试DRL规则文件
     */
    @Test
    public void testDRL() {
        Resource resource = ResourceFactory.newClassPathResource("rules/hello.drl");

        KieHelper kieHelper = new KieHelper();
        kieHelper.addResource(resource, ResourceType.DRL);
        KieSession kieSession = kieHelper.build().newKieSession();

        Person person = Person.builder().name("LeifChen").age(18).build();
        kieSession.insert(person);
        assertEquals(1, kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("test002")));
        kieSession.dispose();
    }

    /**
     * 测试决策表
     */
    @Test
    public void testDT() {
        SpreadsheetCompiler converter = new SpreadsheetCompiler();
        Resource resource = ResourceFactory.newClassPathResource("rules/senior/dt/decisionTable.xlsx");

        String drl;
        try {
            drl = converter.compile(resource.getInputStream(), InputType.XLS);
            log.info("决策表->DRL规则文件转换\n{}", drl);
        } catch (IOException e) {
            log.error("获取文件流失败，{}", e.getMessage());
        }

        KieHelper kieHelper = new KieHelper();
        kieHelper.addResource(resource, ResourceType.DTABLE);
        KieSession kieSession = kieHelper.build().newKieSession();
        Person person = Person.builder().name("张三").age(30).build();
        kieSession.insert(person);
        assertEquals(1, kieSession.fireAllRules());
        kieSession.dispose();
    }

    /**
     * 测试DRT规则模板
     */
    @Test
    public void testDRT() {
        ObjectDataCompiler converter = new ObjectDataCompiler();
        Resource resource = ResourceFactory.newClassPathResource("rules/senior/drt/person.drt");
        Person data = Person.builder().age(18).className("一班").build();

        String drl = null;
        try {
            drl = converter.compile(Collections.singleton(data), resource.getInputStream());
            log.info("DRT->DRL规则文件转换\n{}", drl);
        } catch (IOException e) {
            log.error("获取文件流失败，{}", e.getMessage());
        }

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        KieSession kieSession = kieHelper.build().newKieSession();
        Person person = Person.builder().name("LeifChen").age(18).build();
        kieSession.insert(person);
        assertEquals(1, kieSession.fireAllRules());
        kieSession.dispose();
    }
}
