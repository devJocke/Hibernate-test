package data;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Care", schema = "dbo", catalog = "mobileRemoteDb")
public class Care {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int disciplineId;
    private int playId;
    private int flushId;


    public Care(int disciplineId, int playId, int flushId ) {
        this.disciplineId = disciplineId;
        this.playId = playId;
        this.flushId = flushId;
    }


    private Care( ) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }


    public int getPlayId() {
        return playId;
    }

    public void setPlayId(int playId) {
        this.playId = playId;
    }


    public int getFlushId() {
        return flushId;
    }

    public void setFlushId(int flushId) {
        this.flushId = flushId;
    }


}
