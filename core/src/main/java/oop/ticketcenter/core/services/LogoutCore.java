package oop.ticketcenter.core.services;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.interfaces.users.logout.Logout;
import oop.ticketcenter.core.interfaces.users.logout.LogoutInput;
import oop.ticketcenter.core.interfaces.users.logout.LogoutResult;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutCore implements Logout {
    @Override
    public LogoutResult process(LogoutInput input) {
        ActiveUserSingleton.getInstance().setActiveUser(null);
        return LogoutResult.builder()
                .isSuccessful(true)
                .build();
    }
}
