package Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Map;

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

    @Transient
    private ArrayList<CareInformation> needs = new ArrayList<>();


    Care() {
        setDiscipline(new Discipline());
        setPlay(new Play());
        setFlush(new Flush());
    }

    /**
     * Returns everything that the Unicorn needs attention to
     *
     * @return A summarized map with all column names and their row values
     */
    public ArrayList<CareInformation> getAllCategoriesInNeed() {

        needs.add(getDiscipline());
        needs.add(getPlay());
//        needs.add(getFlush());

        for (CareInformation need : needs) {
            if (need.getCategory().isEmpty()) {
                needs.remove(need);
            }
        }

        return needs;
    }

    private Discipline getDiscipline() {
        return discipline;
    }

    private void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    private Play getPlay() {
        return play;
    }

    private void setPlay(Play play) {
        this.play = play;
    }

    private Flush getFlush() {
        return flush;
    }

    private void setFlush(Flush flush) {
        this.flush = flush;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public interface CareInformation {
        /**
         * @return All subcategories in a category eg
         * {@link Discipline}
         * {@link Play}
         * {@link Flush}
         */
        Map<String, Boolean> getCategory();

        void save(String s);
    }
}
