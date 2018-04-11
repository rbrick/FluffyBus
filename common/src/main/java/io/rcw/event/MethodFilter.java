package io.rcw.event;

import java.lang.reflect.Method;

@FunctionalInterface
public interface MethodFilter {
    boolean filter(Method method);
}
