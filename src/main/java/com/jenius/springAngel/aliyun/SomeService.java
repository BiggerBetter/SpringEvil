package com.jenius.springAngel.aliyun;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SomeService {

    @Autowired
    private DependencyClass dependency;

    /**
     * 需求：
     * 1. 3个分支：
     *    - param == null -> 抛出异常
     *    - param.isEmpty() -> 返回 "empty"
     *    - 否则正常逻辑
     * 2. 循环遍历一个 Map
     * 3. 循环遍历一个 List
     */
    public String process(String param) {
        // 分支 1：param == null
        if (param == null) {
            throw new IllegalArgumentException("param cannot be null");
        }

        // 分支 2：param.isEmpty()
        if (param.isEmpty()) {
            return "empty";
        }

        // 正常逻辑分支 3
        Map<String, String> sampleMap = new HashMap<>();
        sampleMap.put("key1", "val1");
        sampleMap.put("key2", "val2");

        // 遍历 Map
        for (Map.Entry<String, String> entry : sampleMap.entrySet()) {
            dependency.doSomething(entry.getKey(), entry.getValue());
        }

        // 遍历 List
        List<String> sampleList = Arrays.asList("item1", "item2", "item3");
        for (String item : sampleList) {
            dependency.doSomethingElse(item, param);
        }

        return "success";
    }
}
