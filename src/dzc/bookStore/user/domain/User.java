package dzc.bookStore.user.domain;

public class User {
    /**对应数据库表*/
    private String userid;/**主键*/
    private String username;/**用户名*/
    private String user_password;/**密码*/
    private String user_email;/**邮箱*/
    private String user_code;/**激活码*/
    private boolean user_state;/**用户状态（已激活和未激活）*/

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) { this.user_password = user_password; }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public boolean isUser_state() {/**java中boolean类型的get方法写作is*/
        return user_state;
    }

    public void setUser_state(boolean user_state) {
        this.user_state = user_state;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_code='" + user_code + '\'' +
                ", user_state=" + user_state +
                '}';
    }
}
