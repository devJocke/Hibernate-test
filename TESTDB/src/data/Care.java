package data;


import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Care", schema = "dbo", catalog = "mobileRemoteDb")
public class Care extends Needs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disciplineId")
    private Discipline discipline;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playId")
    private Play play;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flushId")
    private Flush flush;

    @Transient
    private Map<String, Boolean> needs = new HashMap<>();


    Care() {
        setDiscipline(new Discipline());
        setPlay(new Play());
        setFlush(new Flush());
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    private void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Play getPlay() {
        return play;
    }

    private void setPlay(Play play) {
        this.play = play;
    }

    public Flush getFlush() {
        return flush;
    }

    private void setFlush(Flush flush) {
        this.flush = flush;
    }

    Map<String, Boolean> getAllNeeds() {
        needs.putAll(getPlay().getNeeds());
        needs.putAll(getDiscipline().getNeeds());
        needs.putAll(getFlush().getNeeds());
        return needs;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
}
