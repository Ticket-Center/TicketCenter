package oop.ticketcenter.core.interfaces.administration.sellers.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditSellerResult implements ProcessorResult {
    private String str;
}
