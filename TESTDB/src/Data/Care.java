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
    private Flush flush;

    @Transient
    private List<CareInformation> needs = new ArrayList<>();


    Care() {
        setDiscipline(new Discipline());
        setPlay(new Play());
        setFlush(new Flush());
    }

    /**
     * Sets all information about the unicorn
     */
    public void loadAllNeeds() {

        if(!needs.contains(getPlay())){
            needs.add(getPlay());
        }

        if(!needs.contains(getDiscipline())){
            needs.add(getDiscipline());
        }

        needs.removeIf(careInformation -> careInformation.getCategories().isEmpty());
    }


    /**
     * Clears all categories that has been fully attended
     * Example if both {@link Play#isBored() && {@link Play#isFootball()}} is done we dont want to see them.
     */
    public void clearAllEmptyNeeds() {
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

     Care newUnicorn() {
        setDiscipline(new Discipline().NewUnicorn());
        setPlay(new Play().NewUnicorn());
        //setFlush(new Flush());
        needs.add(getPlay());
        needs.add(getDiscipline());
        return this;
    }

    public interface CareInformation {
        /**
         * @return All subcategories in a category eg
         * {@link Discipline}
         * {@link Play}
         * {@link Flush}
         */
        LinkedHashMap<String, Boolean>  getCategories();

        String save(String s);
    }
}
