package com.guy7cc.voxelodyssey.core.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExecutionOrder {
    Order value();

    enum Order {
        EARLY,
        MIDDLE,
        LATE
    }
}
