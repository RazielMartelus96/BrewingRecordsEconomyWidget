package io.curiositycore.brewingrecordseconomywidget.model.util;

/**
 * Interface representing the functionality of Builders (as per the Builder Design Pattern)
 * @param <T> Type parameter of class of the instance to be built by the Builder.
 */
public interface Builder<T> {
    /**
     * Build an instance of the Builder's product Class.
     * @return Instance of the Builder's product Class.
     */
    T build();
}
