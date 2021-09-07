package com.chen.drools.service;

import com.chen.drools.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * 高级语法单元测试
 * <p>
 *
 * @Author LeifChen
 * @Date 2021-09-06
 */
@Slf4j
public class SeniorTest {

    private KieContainer kieContainer;
    private KieSession kieSession;

    @Before
    public void before() {
        KieServices kss = KieServices.get();
        kieContainer = kss.getKieClasspathContainer();
    }

    @After
    public void after() {
        if (kieSession != null) {
            kieSession.dispose();
        }
    }

    /**
     * 验证决策表生成的DRL规则文件
     *
     * @throws FileNotFoundException 文件未找到异常
     */
    @Test
    public void verificationDecisionTable() throws FileNotFoundException {
        File file = new File("E:\\hello_workspace\\hello-drools\\src\\main\\resources\\rules\\senior\\decisionTable.xlsx");
        InputStream is = new FileInputStream(file);
        SpreadsheetCompiler converter = new SpreadsheetCompiler();
        String drl = converter.compile(is, InputType.XLS);
        log.info("规则文件:\n{}", drl);
    }

    /**
     * 决策表
     */
    @Test
    public void testDecisionTable() {
        kieSession = kieContainer.newKieSession("decisionTable");
        Person person = Person.builder().name("张三").age(30).build();
        kieSession.insert(person);
        assertEquals(1, kieSession.fireAllRules());
    }
}
