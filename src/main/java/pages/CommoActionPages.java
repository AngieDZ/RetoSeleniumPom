package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class CommoActionPages {

    protected WebDriver driver;
    public CommoActionPages(WebDriver driver) {

        this.driver = driver;
    }
    public void visitarPagina(String url){
        driver.get(url);
    }
    public void limpiarCajaTexto(By localizador){
        WebElement elemento = driver.findElement(localizador);
        elemento.clear();
    }
    public void escribirTexto(By localizador, String texto){
        WebElement elemento = driver.findElement(localizador);
        elemento.sendKeys(texto);
    }
    public void hacerClick(By localizador){
        WebElement elemento = driver.findElement(localizador);
        elemento.click();
    }
    public boolean verificarElementoVisible(By localizador){
        try {
            return driver.findElement(localizador).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void esperarTiempoExplicito(By localizador, int segundos) {
        new WebDriverWait(driver, Duration.ofSeconds(segundos))
                .until(ExpectedConditions.visibilityOfElementLocated(localizador));
    }
    public void verificarPaginaDeInicio(By localizador){
        esperarTiempoExplicito(localizador, 10);
        verificarElementoVisible(localizador);
    }
}
