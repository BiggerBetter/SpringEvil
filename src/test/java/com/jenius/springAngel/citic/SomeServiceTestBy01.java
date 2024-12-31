package com.jenius.springAngel.citic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SomeServiceTestBy01 {

    // 注入模拟对象
    @InjectMocks
    private SomeService someService;

    // 模拟依赖类
    @Mock
    private DependencyClass dependency;

    // 在每个测试方法之前运行
    @BeforeEach
    public void setUp() {
        // 初始化mocks，必须在每个测试之前调用
        MockitoAnnotations.initMocks(this);
    }

    // 测试当参数为null时，是否抛出IllegalArgumentException
    @Test
    public void testProcessNullParam() {
        // 期望抛出IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            someService.process(null);
        });

        // 验证异常信息
        assertEquals("param cannot be null", exception.getMessage());
    }

    // 测试当参数为空字符串时，是否返回 "empty"
    @Test
    public void testProcessEmptyParam() {
        String result = someService.process("");
        assertEquals("empty", result);

        // 验证没有与依赖类发生交互
        verifyNoInteractions(dependency);
    }

    // 测试正常逻辑，验证返回 "success" 并与依赖类交互
    @Test
    public void testProcessSuccess() {
        String param = "testParam";
        String result = someService.process(param);

        // 验证返回值
        assertEquals("success", result);

        // 验证 Map 的交互
        Map<String, String> sampleMap = new HashMap<>();
        sampleMap.put("key1", "val1");
        sampleMap.put("key2", "val2");

        // 验证是否按预期调用了依赖类的 doSomething 方法
        for (Map.Entry<String, String> entry : sampleMap.entrySet()) {
            verify(dependency).doSomething(entry.getKey(), entry.getValue());
        }

        // 验证 List 的交互
        List<String> sampleList = Arrays.asList("item1", "item2", "item3");
        for (String item : sampleList) {
            verify(dependency).doSomethingElse(item, param);
        }
    }
}
