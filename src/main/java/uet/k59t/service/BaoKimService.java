package uet.k59t.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import uet.k59t.model.Student;
import uet.k59t.repository.RecordRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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

    public List<Student> adminFeeList() {
        String sURL = "http://localhost/school1/index.php?admin/list_class"; //just a string
        List<Student> studentList = new ArrayList<>();

        try {
        // Connect to the URL using java's native library
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

        // Convert to a JSON object to print data
            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = null; //Convert the input stream to a json element

            root = jp.parse(new InputStreamReader((InputStream) request.getContent()));

            JsonArray rootobj = root.getAsJsonArray(); //May be an array, may be an object.
            for(int i = 0; i < rootobj.size(); i++) {
                JsonObject studentGson = rootobj.get(i).getAsJsonObject();
                Student student = new Student();
                student.setStudentName(studentGson.get("studentName").getAsString());
                student.setClassName(studentGson.get("className").getAsString());
                student.setParentName(studentGson.get("parentName").getAsString());
                student.setClassId(studentGson.get("class_id").getAsInt());
                student.setParentId(studentGson.get("parent_id").getAsInt());

                if(recordRepository.findByMota(student.getStudentName()) != null){
                    student.setPaymentStatus(true);
                }
                else student.setPaymentStatus(false);
                studentList.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentList;
    }
    public List<Student> getPaymentStatus(String role, String parentName) throws Exception {
        if (role.equalsIgnoreCase("Admin")) {

        }
        if (role.equalsIgnoreCase("Parent")) {
            String sURL = "http://localhost/school1/index.php?admin/list_class"; //just a string

            // Connect to the URL using java's native library
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
            JsonArray listStudent = root.getAsJsonArray(); //May be an array, may be an object.
            List<Student> studentofParent = new ArrayList<Student>();
            for (int i = 0; i < listStudent.size(); i++) {
                if(listStudent.get(i).getAsJsonObject().get("parentName").getAsString().equalsIgnoreCase(parentName)){
                    Student student = new Student();
                    student.setClassName(listStudent.get(i).getAsJsonObject().get("className").getAsString());
                    student.setParentName(listStudent.get(i).getAsJsonObject().get("parentName").getAsString());
                    student.setClassId(listStudent.get(i).getAsJsonObject().get("class_id").getAsInt());
                    student.setParentId(listStudent.get(i).getAsJsonObject().get("parent_id").getAsInt());
                    student.setStudentName(listStudent.get(i).getAsJsonObject().get("studentName").getAsString());
                    studentofParent.add(student);
                }
            }
            List<Student> returnPayment = new ArrayList<Student>();
            for (int i = 0; i < studentofParent.size(); i++) {
                Student student = studentofParent.get(i);
                List<Record> recordList = (List<Record>) recordRepository.findAll();
                for (int j = 0; j < recordList.size(); j++) {
                    if(recordList.get(j).getMota().equalsIgnoreCase(student.getStudentName())){
                        student.setPaymentStatus(true);
                    }
                }
                returnPayment.add(student);
            }
            return returnPayment;

        }
        throw  new Exception("Role is not appropriate");
    }
}
