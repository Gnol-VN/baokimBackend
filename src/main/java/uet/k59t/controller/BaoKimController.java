package uet.k59t.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uet.k59t.model.Record;
import uet.k59t.model.Student;
import uet.k59t.service.BaoKimService;

import java.util.List;

/**
 * Created by Long laptop on 4/20/2018.
 */
@RestController
public class BaoKimController {
    @Autowired
    BaoKimService baoKimService;

    @RequestMapping(value = "updaterecord", method = RequestMethod.GET)
    public List<Record> updateRecord(){
        return baoKimService.updateRecord();
    }
    @RequestMapping(value = "getrecord", method = RequestMethod.GET)
    public List<Record> getRecord(){
        return baoKimService.getRecord();
    }

    @RequestMapping(value = "admingetpayment", method = RequestMethod.GET)
    public List<Student> adminFeeList() {return baoKimService.adminFeeList();}

}
