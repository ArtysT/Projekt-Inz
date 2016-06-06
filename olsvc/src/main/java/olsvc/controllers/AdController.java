package olsvc.controllers;


import olsvc.models.Ad;
import olsvc.models.AdDao;
import olsvc.models.User;
import olsvc.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Random;

@Controller
public class AdController {

    @CrossOrigin
    @RequestMapping(value = "/ad/create", method = RequestMethod.POST)
    public ResponseEntity<Ad> create(@RequestBody Ad create) throws MessagingException {
        if(create!=null){
            adDao.save(create);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @RequestMapping("/ads")
    @ResponseBody
    public Iterable<Ad> getUsersDetails(){
        return adDao.findAll();
    }

    @Autowired
    private AdDao adDao;
    private UserDao userDao;
}
