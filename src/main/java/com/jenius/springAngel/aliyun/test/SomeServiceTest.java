package com.jenius.springAngel.aliyun.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SomeServiceTest {

    @Mock
    private DependencyClass dependency;

    @InjectMocks
    private SomeService someService;

    /**
     * 分支 1：param == null -> 期望抛出异常
     */
    @Test
    void process_shouldThrowExceptionWhenParamIsNull() {
        // 执行 & 验证
        assertThrows(IllegalArgumentException.class, () -> someService.process(null));
    }

    /**
     * 分支 2：param.isEmpty() -> 返回 "empty"
     */
    @Test
    void process_shouldReturnEmptyWhenParamIsEmpty() {
        // 执行
        String result = someService.process("");

        // 验证
        assertEquals("empty", result);
        // 验证 dependency 未被调用
        verifyNoInteractions(dependency);
    }

    /**
     * 分支 3：正常逻辑 -> 应该调用两次 doSomething, 三次 doSomethingElse
     */
    @Test
    void process_shouldCallDependencyMethodsAndReturnSuccess() {
        // 准备参数
        String param = "testParam";

        // 执行
        String result = someService.process(param);

        // 验证返回值
        assertEquals("success", result);

        // 验证对 dependency 的调用次数
        verify(dependency, times(1)).doSomething("key1", "val1");
        verify(dependency, times(1)).doSomething("key2", "val2");

        verify(dependency, times(1)).doSomethingElse("item1", param);
        verify(dependency, times(1)).doSomethingElse("item2", param);
        verify(dependency, times(1)).doSomethingElse("item3", param);

        // 确保没有更多的调用
        verifyNoMoreInteractions(dependency);
    }
}