package org.mtech.application.usecase;

@FunctionalInterface
public interface QueryUseCase<R> {

    R invoke();

}
