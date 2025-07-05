package org.mtech.core.usecase;

@FunctionalInterface
public interface CommandUseCase<C, R> {

    R invoke(C command);

}
