package olsvc.controllers;

import olsvc.models.User;
import olsvc.models.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/create")
    @ResponseBody
    public String create(String email, String login) {
        User user = null;
        try {
            user = new User(email, login);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }

    @RequestMapping("/delete")
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

    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id, String email, String login) {
        try {
            User user = userDao.findOne(id);
            user.setEmail(email);
            user.setName(login);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    @RequestMapping("/{id}")
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
