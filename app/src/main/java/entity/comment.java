package entity;

public class comment {
    private String content;
    private String name;
    private String time;
    private String nickname;

    public comment() {
    }

    public comment(String content, String name, String time) {
        this.content = content;
        this.name = name;
        this.time = time;
    }

    public comment(String content, String name, String time, String nickname) {
        this.content = content;
        this.name = name;
        this.time = time;
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
