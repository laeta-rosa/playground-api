package org.mtech.application.usecase;

@FunctionalInterface
public interface CommandUseCase<C, R> {

    R invoke(C command);

}
