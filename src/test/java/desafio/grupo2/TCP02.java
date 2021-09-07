package desafio.grupo2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TCP02 {
    WebDriver driver;

    @BeforeClass
    public static void init(){
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();//borrar cookies
        driver.manage().window().maximize();

    }

    @After
    public void close(){
        System.out.println("After");
        if(driver != null){
            driver.close();
        }
    }
    /*
    1. Carga home
    2. Hacer clic en "Vuelo + Auto"
    3. Esperar que se abra el formulario
    4.Hacer clic en Origen
    5. Escribir en el campo Origen "cordoba"
    6. Esperar a que cargue la lista de resultados
    7. Seleccionar "Córdoba, Córdoba, Argentina"
    8. Hacer clic en Destino
    9. Escribir en el campo Destino "rosario"
    10. Esperar a que cargue la lista de resultados
    11. Seleccionar "Rosario, Santa Fe, Argentina"
    12. hacer clic en "Buscar"
     */

    @Test
    public void tcp02(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //1
        driver.get("https://www.viajesfalabella.cl/");
        //2
        driver.findElement(By.xpath("//*[@id=\"sboxContainer-packages\"]/div/div/div[3]/div[1]/div[2]/div/div/span[3]/input")).click();

        //4
        WebElement origen= driver.findElement(By.xpath("//*[@id=\"sboxContainer-packages\"]/div/div/div[3]/div[2]/div[2]/div[1]/div/div/div/input"));
        origen.click();
        //5
        origen.sendKeys("cordoba");
        //7
        driver.findElement(By.xpath("//body/div[11]/div[1]/div[1]/ul[1]/li[1]")).click();
        //8
        WebElement destino = driver.findElement(By.xpath("//*[@id=\"sboxContainer-packages\"]/div/div/div[3]/div[2]/div[2]/div[2]/div/div/div/div/input"));
        destino.click();
        //9
        destino.sendKeys("rosario");
        //11
        driver.findElement(By.xpath("//body/div[11]/div[1]/div[1]/ul[1]/li[1]")).click();
        //12
        driver.findElement(By.xpath("//em[contains(text(),'Buscar')]")).click();

        //resultados esperados
        Assert.assertEquals("Ingresa una fecha de partida.", driver.findElement(By.xpath("//span[contains(text(),'Ingresa una fecha de partida.')]")).getText());
        Assert.assertEquals("Ingresa una fecha de regreso.", driver.findElement(By.xpath("//span[contains(text(),'Ingresa una fecha de regreso.')]")).getText());

    }
}
