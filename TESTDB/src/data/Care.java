package data;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Care", schema = "dbo", catalog = "mobileRemoteDb")
public class Care {

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


//    private Care(int disciplineId, int playId, int flushId) {
//        this.disciplineId = disciplineId;
//        this.playId = playId;
//        this.flushId = flushId;
//    }

    public static class CareBuilder {

//        private final int disciplineId;
//        private final int playId;
//        private final int flushId;

//        public CareBuilder(int disciplineId, int playId, int flushId) {
//
//            this.disciplineId = disciplineId;
//            this.playId = playId;
//            this.flushId = flushId;
//        }

        public void build() {
//            return new Care(disciplineId, playId, flushId);
        }
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
}
