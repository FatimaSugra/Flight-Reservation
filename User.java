import java.util.LinkedList;

public class User {
    private String username;
    private String password;
    private String name;
    private String cnic;
    private String phoneNumber;

    private static LinkedList<User> userList = new LinkedList<>();

    public User(String username, String password, String name, String cnic, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.cnic = cnic;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static void register(String username, String password, String name, String cnic, String phoneNumber) {
        userList.add(new User(username, password, name, cnic, phoneNumber));
        System.out.println("User registered successfully!");
    }

    public static boolean login(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return true;
            }
        }
        System.out.println("Invalid credentials!");
        return false;
    }

    public void updateProfile(String newName, String newCnic, String newPhoneNumber) {
        this.name = newName;
        this.cnic = newCnic;
        this.phoneNumber = newPhoneNumber;
        System.out.println("Profile updated successfully!");
    }


    public static User findUserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static User findUserByCnic(String cnic) {
        for (User user : userList) {
            if (user.getCnic().equals(cnic)) {
                return user;
            }
        }
        return null;
    }
}