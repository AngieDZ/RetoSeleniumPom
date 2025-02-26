package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends CommoActionPages{
    public SignupPage(WebDriver driver){

        super(driver);
    }

    private final By mensajeIndicativo = By.xpath("//h2[normalize-space(.)='New User Signup!']");
    private final By inputNombre = By.xpath("//input[@name='name']");
    private final By inputEmail = By.xpath("//input[@data-qa='signup-email']");
    private final By botonSignup = By.xpath("//button[@data-qa='signup-button']");


    private final By mensajeIndicativoLogin = By.xpath("//h2[normalize-space(.)='Login to your account']");
    private final By inputEmailLogin = By.xpath("//input[@data-qa='login-email']");
    private final By inputContrasenia = By.xpath("//input[@data-qa='login-password']");
    private final By botonLogin = By.xpath("//button[@data-qa='login-button']");
    private final By mensajeDatosIncorrectos = By.xpath("//p[normalize-space(text())='Your email or password is incorrect!']");


    public void registrarUsuario(String nombre, String email) {
        esperarTiempoExplicito(mensajeIndicativo, 10);

        if (verificarElementoVisible(mensajeIndicativo)) {
            limpiarCajaTexto(inputNombre);
            escribirTexto(inputNombre, nombre);

            limpiarCajaTexto(inputEmail);
            escribirTexto(inputEmail, email);

            hacerClick(botonSignup);
        } else {
            throw new RuntimeException("El mensaje '¡Registro de nuevo usuario!' no está visible.");
        }
    }

    public void loginIncorrecto (String email, String pass) {

        esperarTiempoExplicito(mensajeIndicativoLogin, 10);

        if (verificarElementoVisible(mensajeIndicativoLogin)) {
            limpiarCajaTexto(inputEmailLogin);
            escribirTexto(inputEmailLogin, email);

            limpiarCajaTexto(inputContrasenia);
            escribirTexto(inputContrasenia, pass);

            hacerClick(botonLogin);
        } else {
            throw new RuntimeException("El mensaje '¡Login to your account!' no está visible.");
        }
    }

    public void verificarDatosIncorrectos() {
        esperarTiempoExplicito(mensajeDatosIncorrectos, 10);
        verificarElementoVisible(mensajeDatosIncorrectos);
    }
}
