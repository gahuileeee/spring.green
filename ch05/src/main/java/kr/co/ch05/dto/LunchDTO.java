package kr.co.ch05.dto;

public class LunchDTO {
    private  String seq;
    private  String name;
    private  String time;
    private  String kind;
    private  String comment;
    private  String score;

    @Override
    public String toString() {
        return "LunchDTO{" +
                "seq='" + seq + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", kind='" + kind + '\'' +
                ", comment='" + comment + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
