package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePages extends CommoActionPages{
    public HomePages(WebDriver driver){

        super(driver);
    }
    private final By botonSignupLogin = By.xpath("//a[contains(text(),'Signup / Login')]");
    private final By botonProducto = By.xpath("//a[normalize-space(text())='Products']");
    private final By botonContacto = By.xpath("//a[@href='/contact_us' and contains(text(), 'Contact us')]");

    public void clickBotonSignupLogin(){
        esperarTiempoExplicito(botonSignupLogin, 10);
        hacerClick(botonSignupLogin);
    }
    public void clickBotonProducto(){
        esperarTiempoExplicito(botonProducto, 10);
        hacerClick(botonProducto);
    }

    public void clickBotonContacto(){
        esperarTiempoExplicito(botonContacto, 10);
        hacerClick(botonContacto);
    }

}
