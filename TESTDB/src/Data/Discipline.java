package Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Discipline implements Care.CareInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean angry = false;

    @Transient
    private
    Map<String, Boolean> map = new HashMap<>();

    Discipline() {
        map.put("angry", false);
    }

    @Override
    public Map<String, Boolean> getCategories() {
        if (!isAngry()) {
            map.put("angry", isAngry());
        }
        return map;
    }

    @Override
    public void save(String s) {
        if (s.equals("angry")) {
            setAngry(true);
            map.remove("angry");
        }
    }


    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private boolean isAngry() {
        return angry;
    }

    public void setAngry(boolean angry) {
        this.angry = angry;
    }

}
