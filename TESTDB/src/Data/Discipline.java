package Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Discipline implements Care.CareInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean angry = false;

    @Transient
    private
    LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();

    Discipline() {  }

   Discipline NewUnicorn() {
        map.put("angry", false);
        return this;
    }

    @Override
    public LinkedHashMap<String, Boolean> getCategories() {
        if (!isAngry()) {
            map.put("angry", isAngry());
        }
        return map;
    }

    @Override
    public String save(String key) {
        if (key.equals("angry")) {
            setAngry(true);
            map.remove("angry");
        }
        return key;
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
