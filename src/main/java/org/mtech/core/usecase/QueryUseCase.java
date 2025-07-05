package org.mtech.core.usecase;

@FunctionalInterface
public interface QueryUseCase<R> {

    R invoke();

}
