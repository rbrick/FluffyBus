package io.rcw.event;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public final class EventConfiguration {
    private List<MethodFilter> methodFilters = new ArrayList<>();

    public <T extends Annotation> AnnotationMethodFilter<T> withAnnotation(Class<T> annotation) {
        AnnotationMethodFilter<T> annotationMethodFilter = new AnnotationMethodFilter<>(annotation, this);
        this.methodFilters.add(annotationMethodFilter);
        return annotationMethodFilter;
    }

    public EventConfiguration withParameters(Class<?>... classes) {
        return this;
    }

    public List<MethodFilter> getFilters() {
        return methodFilters;
    }
}
