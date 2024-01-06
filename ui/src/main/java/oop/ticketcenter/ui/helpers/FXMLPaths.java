package oop.ticketcenter.ui.helpers;

public enum FXMLPaths {
    LOGIN_FORM("/fxmls/login.fxml"),
    FORGOT_PASS_FORM("/fxmls/forgotpassword.fxml"),
    REGISTER_NEW_USER("/fxmls/register.fxml"),
    NAVIGATION_USER("/fxmls/navigation.fxml"),
    NAVIGATION_ADMIN("/fxmls/navigationAdmin.fxml"),
    HOME_PAGE("/fxmls/homePage.fxml"),
    CREATE_EVENT("/fxmls/newEvent.fxml"),
    EDIT_EVENT("/fxmls/editEvent.fxml"),
    DELETE_EVENT("/fxmls/deleteEvent.fxml"),
    DELETE_ADMINISTRATION("/fxmls/deleteAdministration.fxml"),
    REGISTER_NEW_ADMINISTRATION("/fxmls/registerAdministration.fxml"),
    EDIT_ADMINISTRATION("/fxmls/editAdministration.fxml"),
    TICKET("/fxmls/ticket.fxml"),
    USER_PAGE("/fxmls/userPage.fxml"),
    USER("/fxmls/user.fxml");

    private final String path;

    FXMLPaths(String path) {
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }
}
