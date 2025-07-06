package org.mtech.application.usecase;

@FunctionalInterface
public interface QueryUseCaseWithRequest<Q, R> {

    R invoke(Q query);

}
