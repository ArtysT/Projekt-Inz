package olsvc.controllers;

import olsvc.models.User;
import olsvc.models.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) {

        if (user != null){
            userDao.save(user);
            return new ResponseEntity<User>(HttpStatus.CREATED);}
        else
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User login) {
            User user = userDao.findByLogin(login.getLogin());
        if(user == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
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

}
