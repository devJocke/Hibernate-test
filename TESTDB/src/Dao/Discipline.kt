package Dao

import java.util.LinkedHashMap
import javax.persistence.*

@Entity
class Discipline : Care.CareInformation, DaoMaster() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0

    var angry: Boolean = false

    @Transient
    private var unicornProperties = LinkedHashMap<String, Boolean>()

    override fun newUnicorn(): Discipline {
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