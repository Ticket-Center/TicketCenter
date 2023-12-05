package oop.ticketcenter.core.interfaces.events.create;

import oop.ticketcenter.core.interfaces.base.Processor;

public interface CreateEvent extends Processor<CreateEventResult, CreateEventInput> {
}
