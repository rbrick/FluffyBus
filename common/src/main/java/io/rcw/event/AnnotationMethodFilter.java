package io.rcw.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class AnnotationMethodFilter<T extends Annotation> implements MethodFilter {
    private Class<T> markedWith;
    private String key = "value";
    private Object value;

    private EventConfiguration parent;

    public AnnotationMethodFilter(Class<T> markedWith, EventConfiguration parent) {
        this.markedWith = markedWith;
        this.parent = parent;
    }

    public EventConfiguration value(Object value) {
         this.value = value;
         return this.parent;
    }

    public EventConfiguration value(String key, Object value) {
        this.key = key;
        this.value = value;
        return this.parent;
    }

    @Override
    public boolean filter(Method method) {
        if (method.isAnnotationPresent(markedWith)) {
            if (value != null) {
                T annotation = method.getAnnotation(markedWith);
                for (Method m : markedWith.getMethods()) {
                    if (m.getName().equals(key)) {
                        if (!m.getReturnType().equals(Void.TYPE) && m.getReturnType().equals(value.getClass())) {
                            try {
                                Object v = m.invoke(annotation);
                                if (v.equals(value)) {
                                    return true;
                                }
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
