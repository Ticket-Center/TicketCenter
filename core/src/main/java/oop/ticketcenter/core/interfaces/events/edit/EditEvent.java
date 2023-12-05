package oop.ticketcenter.core.interfaces.events.edit;

import oop.ticketcenter.core.interfaces.base.Processor;

public interface EditEvent extends Processor<EditEventResult, EditEventInput> {
}
