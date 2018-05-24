package uet.k59t.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uet.k59t.model.BaoKimList;
import uet.k59t.model.Record;
import uet.k59t.repository.RecordRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Long laptop on 4/20/2018.
 */
@Service
public class BaoKimService {
    @Autowired
    RecordRepository recordRepository;
    public List<Record> updateRecord(){
//        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Long laptop\\Desktop\\schoolMessage\\baokimBackend\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
        File file = new File("C:/Users/Long laptop/Desktop/schoolMessage/baokimBackend/phantomjs.exe");
        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
        WebDriver driver = new PhantomJSDriver();
        driver.get("https://www.baokim.vn/giao-dich/lich-su-giao-dich"); //launch Firefox and open Url

        //Tìm ô input trong css và nhập thông tin ID, password
        WebElement username = driver.findElement(By.cssSelector("#LoginForm_username_form")); //find element by path
        username.sendKeys("nambeca@gmail.com"); //send text to textbox
        WebElement password = driver.findElement(By.cssSelector("#LoginForm_password_form")); //find element by path
        password.sendKeys("12345678"); //send text to textbox
        //click nút
        WebElement button = driver.findElement(By.cssSelector("#login-form > div > div:nth-child(3) > button")); //find element by xpath - name
        button.click(); //click button to verify email

        //Lấy tất cả phần tử trên màn hình
        List<WebElement> tableData = driver.findElements(By.cssSelector(".row-detail td"));
        //Tạo đối tượng lưu trữ

        BaoKimList<Record> baoKimList= new BaoKimList<>();
        baoKimList.addAll((Collection<? extends Record>) recordRepository.findAll());

        for (int i = 0, rowindex =2; i < tableData.size(); i=i+10, rowindex=rowindex+1) {
            List<WebElement> tableData1 = driver.findElements(By.cssSelector(".row-detail td"));
            Record aRecord = new Record(
                    tableData1.get(i).getText(),
                    tableData1.get(i+1).getText(),
                    tableData1.get(i+2).getText(),
                    tableData1.get(i+3).getText(),
                    tableData1.get(i+4).getText(),
                    tableData1.get(i+5).getText(),
                    tableData1.get(i+6).getText(),
                    tableData1.get(i+7).getText(),
                    tableData1.get(i+8).getText()
            );
            //start
            if(baoKimList.contains(aRecord)){
                continue;
            }
            WebElement buttonChiTiet = driver.findElement(By.cssSelector("#content > div.bkui.transaction-history > table.table.table-bordered > tbody > tr:nth-child("+rowindex+") > td:nth-child(10) > a"));
            buttonChiTiet.click();
            String mota = driver.findElement(By.cssSelector("#transfer-form > div:nth-child(2) > ul.blue-bk > li:nth-child(2)")).getText();
            try{
                mota = mota.substring(14);
            }catch (Exception e){

            }
            aRecord.setMota(mota);
            driver.navigate().back();
            //end
            baoKimList.add(aRecord);
            recordRepository.save(aRecord);

        }


        driver.close(); //close chrome
        return baoKimList;
    }

    public List<Record> getRecord() {
        return (List<Record>) recordRepository.findAll();
    }
}
