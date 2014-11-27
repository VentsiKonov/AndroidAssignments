package me.vkonov.classassignmentfour;


public class User {

    public enum Role{
        User,
        Admin
    }
    // Class vars
    private static int ID = 0;

    // Arbitrary fields
    private final int id;
    private String email;
    private String password;
    private Role role;

    // Non-arbitrary fields
    private String phone;
    private String name;


    public int getId() {
        return id;
    }

    public User(String email, String password, Role role) throws Exception{
        setEmail(email);
        setPassword(password);
        setRole(role);
        this.id = ID++;
    }

    // Used for matching
    public User(String email, String password) throws Exception{
        setEmail(email);
        setPassword(password);
        this.id = -1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        if(email.isEmpty())
            throw new Exception("Username cannot be empty!");

        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception{
        if(password.isEmpty())
            throw new Exception("Password cannot be empty!");

        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role){

        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equals(user.email) && password.equals(user.password);

    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
