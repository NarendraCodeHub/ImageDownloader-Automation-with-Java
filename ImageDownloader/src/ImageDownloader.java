

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

public class ImageDownloader {
    
    public static void main(String[] args) {
        // Create a Folder with name download_images
        String folderPath = "download_images";
        createFolder(folderPath);
        
        //  Launch Firefox browser and hit the URL
        System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        //Enter the URL
        driver.get("https://webkul.com/blog/marketplace-seller-badge-woocommerce/");
        
        //  Select all images and download
        List<WebElement> images = driver.findElements(By.tagName("img"));
        for (WebElement image : images) {
            String imageURL = image.getAttribute("src");
            downloadImage(imageURL, folderPath);
        }
        
        //close the Browser
        driver.quit();
    }
    
    //Creating Folder
    private static void createFolder(String folderPath) {
    	 File folder = new File(folderPath);
         if (!folder.exists()) {
             folder.mkdir();
         }
    }
    
    //Pass one by one images and download 
    private static void downloadImage(String imageURL, String folderPath) {
        try {
            URL url = new URL(imageURL);
            InputStream is = url.openStream();
            String[] urlParts = imageURL.split("/");
            String imageName = urlParts[urlParts.length - 1];
            OutputStream os = new FileOutputStream(folderPath + "/" + imageName);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
