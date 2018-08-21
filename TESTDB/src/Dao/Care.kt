package Dao

import javax.persistence.*
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
    private val needs = mutableListOf<CareInformation>()


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

    fun loadAllNeeds() {
        needs.addIfHasCategories(play)
        needs.addIfHasCategories(discipline)
        needs.addIfHasCategories(toilet)
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
        fun getCategories(): LinkedHashMap<String, Boolean>
        fun newUnicorn(): Care.CareInformation
        fun save(key: String): String
        fun checkForUpdates()
    }

    /**
     * If there are no categories in provided careinformation, remove it from the list so we dont display it for the user
     * else if the list does not contain the careinformation add it
     */
    private fun MutableList<Care.CareInformation>.addIfHasCategories(careInformation: Care.CareInformation) {
        careInformation.checkForUpdates()
        if (careInformation.getCategories().isEmpty()) {
            remove(careInformation)
        } else if (!contains(careInformation)) {
            add(careInformation)
        }
    }
}
