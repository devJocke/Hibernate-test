package Dao

import java.util.*
import javax.persistence.*


@Entity
class Play : Care.CareInformation, DaoMaster() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    /**
     * Gets set using reflection api
     * @see DaoMaster
     */
    private var hockey = false
    private var football = false

    @Transient
    private var unicornProperties = LinkedHashMap<String, Boolean>()


    override fun newUnicorn(): Play {
        unicornProperties = setNewUnicorn(unicornProperties)
        return this
    }

    /**
     * Adds false values to the unicornProperties
     */
    override fun checkForUpdates() {
        unicornProperties = checkAndReturnUpdates(unicornProperties)
    }

    override fun save(key: String): String {
        unicornProperties.remove(saveChanges(key))
        return "enjoyed playing $key"
    }

    override fun getCategories(): LinkedHashMap<String, Boolean> {
        return unicornProperties
    }

    override fun toString(): String {
        return javaClass.simpleName
    }
}