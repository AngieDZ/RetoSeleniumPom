package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ContactPage extends CommoActionPages{

    public ContactPage(WebDriver driver){
        super(driver);
    }

    private final By etiquetaGetInTouch = By.xpath("//h2[text()='Get In Touch']");
    private final By inputNombre = By.xpath("//input[@data-qa='name']");
    private final By inputEmail = By.xpath("//input[@data-qa='email']");
    private final By inputAsunto = By.xpath("//input[@data-qa='subject']");
    private final By inputMensaje = By.xpath("//textarea[@data-qa='message']");
    private final By inputArchivo = By.xpath("//input[@name='upload_file']");
    private final By botonEnviar = By.xpath("//input[@data-qa='submit-button']");
    private final By etiquetaExito = By.xpath("//div[contains(@class, 'status') and contains(@class, 'alert-success')]");
    private final By botonInicio = By.xpath("//a[contains(@class, 'btn-success') and contains(@href, '/')]");

    private final By imgInicio = By.xpath("//img[@src='/static/images/home/logo.png']");
    public void llenarFormularion(String nombre, String email, String asunto, String mensaje, String rutaArchivo) {

        esperarTiempoExplicito(etiquetaGetInTouch, 10);

        if (!verificarElementoVisible(etiquetaGetInTouch)) {
            throw new RuntimeException("El mensaje 'Get In Touch' no está visible.");
        }

            limpiarCajaTexto(inputNombre);
            escribirTexto(inputNombre, nombre);

            limpiarCajaTexto(inputEmail);
            escribirTexto(inputEmail, email);

            limpiarCajaTexto(inputAsunto);
            escribirTexto(inputAsunto, asunto);

            limpiarCajaTexto(inputMensaje);
            escribirTexto(inputMensaje, mensaje);

            if(rutaArchivo != null && !rutaArchivo.isEmpty()){
                cargarArchivo(inputArchivo, rutaArchivo);
            }
    }
    private void cargarArchivo(By selectorInput, String rutaArchivo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement inputArchivo = wait.until(ExpectedConditions.elementToBeClickable(selectorInput));
        inputArchivo.sendKeys(rutaArchivo);
    }
    public void enviarFormulario() {
        hacerClick(botonEnviar);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public boolean verificarMensajeExito() {
        esperarTiempoExplicito(etiquetaExito, 10);
        return verificarElementoVisible(etiquetaExito);
    }

    public void regresarInicio() {
        hacerClick(botonInicio);
        verificarPaginaDeInicio(imgInicio);

    }
}
