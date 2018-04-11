package io.rcw.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class ClassInvocationHandler {
    private Class<?> clazz;
    private Object instance;
    private List<Method> methods;

    /*
    TODO: Guice/Dagger support
    public ClassInvocationHandler(Class<?> clazz, Injector injector) {
        this.clazz = clazz;
        this.instance = injector.getInstance(clazz);
    }
    */

    public ClassInvocationHandler(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        this.clazz = clazz;
        this.instance = clazz.newInstance();
    }

    public ClassInvocationHandler(Object instance) {
        this.instance = instance;
        this.clazz = instance.getClass();
    }

    public Object getInstance() {
        return instance;
    }

    public Class<?> getWrappedClass() {
        return clazz;
    }

    public List<Method> filteredMethods(EventConfiguration settings) {
        List<Method> methods = new ArrayList<>();
        for (Method method : clazz.getMethods()) {
            for (MethodFilter filter : settings.getFilters()) {
                if (!filter.filter(method)){
                    methods.remove(method);
                    continue;
                }
                methods.add(method);
            }
        }
        return methods;
    }
}
