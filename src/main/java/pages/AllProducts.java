package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class AllProducts extends CommoActionPages{

    public AllProducts(WebDriver driver){
        super(driver);
    }

    private final By etiquetaAll = By.xpath("//h2[normalize-space(text())='All Products']");
    private final By inputBusqueda = By.xpath("//input[@id='search_product']");
    private final By botonBusqueda = By.xpath("//button[@id='submit_search']");
    private final By etiquetaSearched = By.xpath("//h2[@class='title text-center' and text()='Searched Products']");
    private final By productosRelacionados = By.xpath("//div[@class='productinfo text-center']");

    public void buscarProducto(String producto){

        if(verificarElementoVisible(etiquetaAll)){

            limpiarCajaTexto(inputBusqueda);
            escribirTexto(inputBusqueda, producto);
            hacerClick(botonBusqueda);

            if(!verificarElementoVisible(etiquetaSearched)){
                throw new RuntimeException("La etiqueta 'Searched Products' no está visible.");
            }
            verificarProductoRelacionado(producto);
        } else {
            throw new RuntimeException("Todos los productos no estan visible.");
        }
    }

    private void verificarProductoRelacionado(String productoEsperado) {


        List<WebElement> productosEncontrados = driver.findElements(productosRelacionados);

        if (productosEncontrados.isEmpty()){
            throw new RuntimeException("No se encontro producto relacionado con la busqueda.");
        }

        for (WebElement producto : productosEncontrados){
            String nombreProducto = producto.getText().trim();


            if (nombreProducto.toLowerCase().contains(productoEsperado.toLowerCase())){
                return;
            }
        }
        throw new RuntimeException("No se encontró coincidencia con: " + productoEsperado);
    }
}
