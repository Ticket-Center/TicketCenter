package oop.ticketcenter.core.interfaces.administration.sellers.register;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSellerResult implements ProcessorResult {
    private String str;
}
