package oop.ticketcenter.ui.helpers;

public enum FXMLPaths {
    LOGIN_FORM("/fxmls/login.fxml"),
    FORGOT_PASS_FORM("/fxmls/forgotpassword.fxml"),
    REGISTER_NEW_USER("/fxmls/register.fxml"),
    NAVIGATION_USER("/fxmls/navigation.fxml"),
    NAVIGATION_ADMIN("/fxmls/navigationAdmin.fxml"),
    HOME_PAGE("/fxmls/homePage.fxml"),
    TICKET("/fxmls/ticket.fxml");

    private final String path;

    FXMLPaths(String path) {
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }
}
