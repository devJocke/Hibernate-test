package Data;


import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Flush {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean toilet = false;
    @Transient
    private Map<String, Boolean> map = new HashMap<>();

    public Flush() {
    }


     public Map<String, Boolean> getNeeds() {
        if (!isToilet()) {
            map.put("toilet", isToilet());
        }
        return map;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }


    public boolean isToilet() {
        return toilet;
    }

    public void setToilet(boolean toilet) {
        this.toilet = toilet;
    }

}
