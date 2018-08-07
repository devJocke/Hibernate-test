package Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
    private Toilet toilet;

    @Transient
    private List<CareInformation> needs = new ArrayList<>();


    Care() {
        setDiscipline(new Discipline());
        setPlay(new Play());
        setToilet(new Toilet());
    }

    /**
     * Sets all information about the unicorn
     */
    public void loadAllNeeds() {

        if (!needs.contains(getPlay())) {
            needs.add(getPlay());
        }

        if (!needs.contains(getDiscipline())) {
            needs.add(getDiscipline());
        }
        if (!needs.contains(getToilet())) {
            needs.add(getToilet());
        }
        needs.removeIf(careInformation -> careInformation.getCategories().isEmpty());
    }

    public List<CareInformation> getNeeds() {
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

    private Toilet getToilet() {
        return toilet;
    }

    private void setToilet(Toilet toilet) {
        this.toilet = toilet;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    Care newUnicorn() {
        setDiscipline(new Discipline().NewUnicorn());
        setPlay(new Play().NewUnicorn());
        setToilet(new Toilet().NewUnicorn());
        needs.add(getPlay());
        needs.add(getDiscipline());
        needs.add(getToilet());
        return this;
    }

    public interface CareInformation {
        /**
         * @return All subcategories in a category eg
         * {@link Discipline}
         * {@link Play}
         * {@link Toilet}
         */
        LinkedHashMap<String, Boolean> getCategories();

        String save(String key);
    }
}
