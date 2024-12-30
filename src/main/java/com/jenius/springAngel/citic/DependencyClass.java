package com.jenius.springAngel.citic;

import org.springframework.stereotype.Component;

@Component
public class DependencyClass {

    public void doSomething(String key, String value) {
        // 实际业务逻辑
        System.out.println("doSomething: key=" + key + ", value=" + value);
    }

    public void doSomethingElse(String item, String param) {
        // 实际业务逻辑
        System.out.println("doSomethingElse: item=" + item + ", param=" + param);
    }
}