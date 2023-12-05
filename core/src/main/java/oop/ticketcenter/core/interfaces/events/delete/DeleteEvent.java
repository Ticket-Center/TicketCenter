package oop.ticketcenter.core.interfaces.events.delete;

import oop.ticketcenter.core.interfaces.base.Processor;

public interface DeleteEvent extends Processor<DeleteEventResult, DeleteEventInput> {
}
