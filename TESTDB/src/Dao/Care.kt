package Dao

import javax.persistence.*
import java.util.ArrayList
import java.util.LinkedHashMap

@Entity
@Table(name = "Care", schema = "dbo", catalog = "mobileRemoteDb")
class Care {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
        private set

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "disciplineId")
    private var discipline: Discipline

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "playId")
    private var play: Play

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "toiletId")
    private var toilet: Toilet

    @Transient
    private val needs = ArrayList<CareInformation>()


    init {
        discipline = Discipline()
        play = Play()
        toilet = Toilet()
    }

      fun newUnicorn(): Care {
        discipline = Discipline().newUnicorn()
        play = Play().newUnicorn()
        toilet = Toilet().newUnicorn()
        needs.add(play)
        needs.add(discipline)
        needs.add(toilet)
        return this
    }

    /**
     * Sets all information about the unicorn
     */
    fun loadAllNeeds() {

        if (!needs.contains(play)) {
            needs.add(play)
        }

        if (!needs.contains(discipline)) {
            needs.add(discipline)
        }
        if (!needs.contains(toilet)) {
            needs.add(toilet)
        }
        needs.removeIf { careInformation -> careInformation.categories().isEmpty() }
    }

    fun getNeeds(): List<CareInformation> {
        return needs
    }


    interface CareInformation {
        /**
         * @return All subcategories in a category eg
         * [Discipline]
         * [Play]
         * [Toilet]
         */
        fun categories(): LinkedHashMap<String, Boolean>

        fun newUnicorn(): Care.CareInformation
        fun save(key: String): String
    }
}
