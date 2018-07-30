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

    Discipline() {
    }


    @Transient
    private
    Map<String, Boolean> map = new HashMap<>();

    @Override
    public Map<String, Boolean> getCategory() {
        if (!isAngry()) {
            map.put("angry", isAngry());
        }
        return map;
    }

    @Override
    public void save(String s) {
        if (s.equals("angry")) {
            map.put("angry", isAngry());
            setAngry(true);
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
