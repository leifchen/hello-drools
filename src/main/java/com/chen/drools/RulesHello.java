package com.chen.drools;

import com.chen.drools.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Hello Drools
 * <p>
 *
 * @Author LeifChen
 * @Date 2021-07-31
 */
@Slf4j
public class RulesHello {

    public static void main(String[] args) {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("helloDrools");
        Person person = new Person();
        person.setName("LeifChen");
        person.setAge(18);
        ks.insert(person);
        int count = ks.fireAllRules();
        log.info("修改后的对象:{}", person);
        log.info("总执行了{}条规则", count);
        ks.dispose();
    }
}
