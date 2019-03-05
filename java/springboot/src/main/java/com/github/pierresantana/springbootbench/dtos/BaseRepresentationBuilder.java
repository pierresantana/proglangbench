package com.github.pierresantana.springbootbench.dtos;

public interface BaseRepresentationBuilder<E, D, B> {

    D toRepresentation(E entity);

    E fromRepresentation(D representation, B builder);
}
