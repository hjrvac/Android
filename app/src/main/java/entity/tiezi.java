package entity;

import java.io.Serializable;

public class tiezi implements Serializable {
    private String title;
    private String name;
    private String introduce;
    private int id;
    private String nickname;
    private String subject;

    public tiezi(String title, String name, String introduce) {
        this.title = title;
        this.name = name;
        this.introduce = introduce;
    }

    public tiezi(String title, String name, String introduce, int id) {
        this.title = title;
        this.name = name;
        this.introduce = introduce;
        this.id = id;
    }

    public tiezi(String title, String name, String introduce, int id, String nickname) {
        this.title = title;
        this.name = name;
        this.introduce = introduce;
        this.id = id;
        this.nickname = nickname;
    }



    public tiezi(String title, String name, String introduce, int id, String nickname, String subject) {
        this.title = title;
        this.name = name;
        this.introduce = introduce;
        this.id = id;
        this.nickname = nickname;
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
