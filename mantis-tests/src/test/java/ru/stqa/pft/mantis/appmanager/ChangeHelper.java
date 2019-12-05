package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.User;

import java.util.List;

public class ChangeHelper extends HelperBase {
    public ChangeHelper(ApplicationManager app) {
        super(app);
    }

    public User resetPassword(){
        driver.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[value='Login']"));
        driver.findElement(By.partialLinkText("Manage Users")).click();
        List<WebElement> userLinks = driver.findElements(By.xpath("//a[starts-with(@href, 'manage_user_edit_page.php')]"));
        for (WebElement userLink : userLinks) {
            if (!userLink.getAttribute("href").contains("user_id=1")){
                userLink.click();
                break;
            }
        }
        String username = driver.findElement(By.xpath("//input[@name='username']")).getAttribute("value");
        String email = driver.findElement(By.xpath("//input[@name='email']")).getAttribute("value");
        driver.findElement(By.xpath("//input[@value='Reset Password']")).click();
        User user = new User(username, email);
        return user;
    }

    public void changePassword(String confirmationLink, String password){
        driver.get(confirmationLink);
        type(By.name("password"),password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
