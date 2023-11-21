package oop.ticketcenter.core.interfaces.base;

public interface Processor<R extends ProcessorResult, I extends ProcessorInput> {
    R process(I input);
}
