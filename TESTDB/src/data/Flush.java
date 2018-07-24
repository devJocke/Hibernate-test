package data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flush {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String flush  = "Default";

  public Flush(){}

  public int getId() {
    return id;
  }

  private void setId(int id) {
    this.id = id;
  }


  public String getFlush() {
    return flush;
  }

  private void setFlush(String flush) {
    this.flush = flush;
  }

}
