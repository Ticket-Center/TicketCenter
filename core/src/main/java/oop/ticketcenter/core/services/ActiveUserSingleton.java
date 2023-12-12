package oop.ticketcenter.core.services;

import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
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
