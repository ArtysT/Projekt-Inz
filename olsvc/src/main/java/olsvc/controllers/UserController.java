package olsvc.controllers;

import olsvc.mail.SmtpMailSender;
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
public class UserController {

    @CrossOrigin
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User create) throws MessagingException {
        User user = userDao.findByLogin(create.getLogin());
        User userr = userDao.findByEmail(create.getEmail());
        if (user != null || userr != null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        else if(create!=null){
            Random generator = new Random();
            int i = generator.nextInt(9000)+1000;
            create.setAKey(i);
            userDao.save(create);
            smtpMailSender.send(create.getEmail(), "Aktywacja konta", "Klikij poniższy link aby aktywować konto: <br/>http://localhost:8080/user/activate?name=" + create.getLogin() + "&key=" + i + "<br/><br/>Ta wiadomość została wygenerowana automatycznie.");
            return new ResponseEntity(HttpStatus.CREATED);
        }
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User login) {
            User user = userDao.findByLogin(login.getLogin());
        if(user == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else if(user.isActivated() == false){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        else if (login.getPassword().equals(user.getPassword())){
            return new ResponseEntity(user.getId() , HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/user/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        }
        catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

    @RequestMapping("/user/update")
    @ResponseBody
    public String updateUser(long id, String email, String login) {
        try {
            User user = userDao.findOne(id);
            user.setEmail(email);
            user.setLogin(login);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUserDetails(@PathVariable("id") long id){
        return userDao.findOne(id);
    }

    @RequestMapping("/users")
    @ResponseBody
    public Iterable<User> getUsersDetails(){
        return userDao.findAll();
    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private SmtpMailSender smtpMailSender;

    @CrossOrigin
    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    @ResponseBody
    public void getByEmail(String email) throws MessagingException {
        String userPass = "";
        try {
            User user = userDao.findByEmail(email);
            userPass = String.valueOf(user.getPassword());
        }
        catch (Exception ex) {
        }
        smtpMailSender.send(email, "Przpominienie hasła", "Twoje hasło do serwisu to: <br/>" + userPass + "<br/><br/>Ta wiadomość została wygenerowana automatycznie.");
    }

    @RequestMapping("/user/activate")
    @ResponseBody
    public void getByEmail(String name, int key) {
        try {
            User user = userDao.findByLogin(name);
            if(user.getAKey() == key){
                user.setActivated(true);
                userDao.save(user);
            }
        }
        catch (Exception ex) {
        }
    }


}
