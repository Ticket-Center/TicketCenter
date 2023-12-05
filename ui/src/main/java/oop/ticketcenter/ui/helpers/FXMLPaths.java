package oop.ticketcenter.ui.helpers;

public enum FXMLPaths {
    LOGIN_FORM("/fxmls/login.fxml"),
    FORGOT_PASS_FORM("/fxmls/forgotpassword.fxml"),
    REGISTER_NEW_USER("/fxmls/registernewuser.fxml");

    private String path;

    FXMLPaths(String path) {
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }
}
