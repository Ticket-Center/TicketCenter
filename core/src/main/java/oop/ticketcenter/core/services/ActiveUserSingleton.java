package oop.ticketcenter.core.services;

import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.UUID;


public class ActiveUserSingleton {
    private static  ActiveUserSingleton activeUserSingleton = null;
    @Getter
    @Setter
    private UUID activeUser;

    private ActiveUserSingleton(){}
    public static ActiveUserSingleton getInstance(){
        if(activeUserSingleton == null){
            activeUserSingleton = new ActiveUserSingleton();
        }
        return activeUserSingleton;
    }

}
