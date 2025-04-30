package Controller;

import DAO.UserDAO;
import Model.User;
import java.util.List;

public class UserController {
    private final UserDAO userDAO = new UserDAO();

    public User registerUser(String username, String password) {
        if (userDAO.isUsernameExists(username)) {
            System.out.println("Tên đăng nhập đã tồn tại, vui lòng nhập tên khác");
            return null;
        }
        else{
            System.out.println("Đăng ký thành công: " + username);
            return userDAO.registerUser(username, password);
        }

    }

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    public boolean logout(int userId) {
        return userDAO.logout(userId);
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean resetPassword(int userId, String newPassword) {
        return userDAO.resetPassword(userId, newPassword);
    }
}
