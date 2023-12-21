package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import lombok.Setter;
import oop.ticketcenter.persistence.enums.Roles;

import java.util.UUID;


public class ActiveUserSingleton {
    private static  ActiveUserSingleton activeUserSingleton = null;
    @Getter
    @Setter
    private UUID activeUser;
    @Getter
    @Setter
    private Roles userRole;
    @Getter
    @Setter
    private String username;


    private ActiveUserSingleton(){}
    public static ActiveUserSingleton getInstance(){
        if(activeUserSingleton == null){
            activeUserSingleton = new ActiveUserSingleton();
        }
        return activeUserSingleton;
    }

}
