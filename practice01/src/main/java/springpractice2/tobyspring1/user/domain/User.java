package springpractice2.tobyspring1.user.domain;

public class User {
    String id;
    String name;
    String password;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    // 필수!!! 자바빈의 규약을 따르는 클래스에 생성자를 명시적으로 추가했을 때는 디폴트 생성자도 함께 정의를 해줘야한다
    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
