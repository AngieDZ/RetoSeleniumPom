package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage extends CommoActionPages {

    WebDriverWait wait;
    public RegisterPage(WebDriver driver){
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public final By mensajeIndicativo = By.xpath("//b[normalize-space(.)='Enter Account Information']");
    private final By botonOpcionMrs = By.xpath("//input[@id='id_gender2']");
    private final By contrasenia = By.xpath("//input[@id='password']");
    private final By seleccionarDia = By.xpath("//select[@id='days']");
    private final By seleccionarMes = By.xpath("//select[@id='months']");
    private final By seleccionarAnio = By.xpath("//select[@id='years']");
    private final By casillaBoletin = By.xpath("//input[contains(@id, 'newsletter')]");
    private final By casillaVerificacion = By.xpath("//input[@id='optin']");
    private final By inputNombre = By.xpath("//input[@id='first_name']");
    private final By inputApellido = By.xpath("//input[@id='last_name']");
    private final By inputDireccion = By.xpath("//input[@id='address1']");
    private final By inputEstado = By.xpath("//input[@id='state']");
    private final By inputCiudad = By.xpath("//input[@id='city']");
    private final By inputPostal = By.xpath("//input[@id='zipcode']");
    private final By inputNumero = By.xpath("//input[@id='mobile_number']");
    private final By botonCreate = By.xpath("//button[normalize-space(.)='Create Account']");
    private final By mensajeExitoso = By.xpath("//h2[@data-qa='account-created']");
    private final By botonContinuar = By.xpath("//a[@data-qa='continue-button']");
    public final By opcionLogged = By.xpath("//a[i[contains(@class, 'fa-user')]]");
    private final By botonBorrar = By.xpath("//a[@href='/delete_account']");
    public final By mensajeCuentaEliminada = By.xpath("//b[normalize-space(text())='Account Deleted!']");

    private final By continuar = By.xpath("//a[@data-qa='continue-button']");

    public void completarFormulario(String pass, String dia, String mes, String anio, String nombre,
                                    String apellido, String direccion, String estado, String ciudad,
                                    String postal, String numero) {
        esperarTiempoExplicito(mensajeIndicativo, 10);
        if (verificarElementoVisible(mensajeIndicativo)) {
            hacerClick(botonOpcionMrs);
            escribirTexto(contrasenia, pass);
            seleccionarFechaNacimiento(dia, mes, anio);
            clickNewsletterCheckbox(casillaBoletin);
            hacerClick(casillaVerificacion);
            escribirTexto(inputNombre, nombre);
            escribirTexto(inputApellido, apellido);
            escribirTexto(inputDireccion, direccion);
            escribirTexto(inputEstado, estado);
            escribirTexto(inputCiudad, ciudad);
            escribirTexto(inputPostal, postal);
            escribirTexto(inputNumero, numero);
            hacerClick(botonCreate);
        } else {
            throw new RuntimeException("No se cargó el formulario de registro correctamente.");
        }
    }

    public void verificarCuentaCreada() {

        esperarTiempoExplicito(mensajeExitoso, 10);

        if (verificarElementoVisible(mensajeExitoso)) {
            hacerClick(botonContinuar);
            verificarElementoVisible(opcionLogged);
        } else {
            throw new RuntimeException("El mensaje de cuenta creada no apareció.");
        }
    }

    public void eliminarCuenta() {

        esperarTiempoExplicito(botonBorrar, 20);
        hacerClick(botonBorrar);

        if (verificarElementoVisible(mensajeCuentaEliminada)) {
            esperarTiempoExplicito(continuar, 10);
            hacerClick(continuar);
        } else {
            throw new RuntimeException("No se eliminó la cuenta correctamente.");
        }
    }

    private void seleccionarFechaNacimiento(String dia, String mes, String anio) {
        new Select(driver.findElement(seleccionarDia)).selectByVisibleText(dia);
        new Select(driver.findElement(seleccionarMes)).selectByVisibleText(mes);
        new Select(driver.findElement(seleccionarAnio)).selectByVisibleText(anio);
    }

    private void clickNewsletterCheckbox(By casillaBoletin) {

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("aswift_3_host")));
        } catch (Exception ignored) {
        }
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(casillaBoletin));
        checkbox.click();

    }
}