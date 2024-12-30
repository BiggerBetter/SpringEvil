package com.jenius.springAngel.citic;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SomeServiceTestByAliyun {

    @Mock
    private DependencyClass dependency;

    @InjectMocks
    private SomeService someService;

    @BeforeEach
    public void setUp() {
        // 使用@InjectMocks自动注入mock
    }

    @Test
    public void process_NullParam_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> someService.process(null));
    }

    @Test
    public void process_EmptyParam_ReturnsEmpty() {
        String result = someService.process("");
        assertEquals("empty", result);
    }

    @Test
    public void process_NonEmptyParam_ReturnsSuccess() {
        String result = someService.process("nonEmpty");
        assertEquals("success", result);

        // 验证doSomething是否被正确调用
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("key1", "val1");
        expectedMap.put("key2", "val2");
        for (Map.Entry<String, String> entry : expectedMap.entrySet()) {
            verify(dependency, times(1)).doSomething(entry.getKey(), entry.getValue());
        }

        // 验证doSomethingElse是否被正确调用
        // List<String> expectedList = List.of("item1", "item2", "item3");
        List<String> expectedList = Collections.unmodifiableList(
                Arrays.asList("item1", "item2", "item3")
        );
        for (String item : expectedList) {
            verify(dependency, times(1)).doSomethingElse(item, "nonEmpty");
        }
    }
}