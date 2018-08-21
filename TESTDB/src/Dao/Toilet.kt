package Dao


import javax.persistence.*
import java.util.LinkedHashMap

@Entity
class Toilet : Care.CareInformation, DaoMaster() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
        private set

    var flush = false

    @Transient
    private var unicornProperties = LinkedHashMap<String, Boolean>()


    override fun newUnicorn(): Toilet {
        unicornProperties = setNewUnicorn(unicornProperties)
        return this
    }

    override fun checkForUpdates() {
        unicornProperties = checkAndReturnUpdates(unicornProperties)

    }

    override fun save(key: String): String {
        unicornProperties.remove(saveChanges(key))
        return "is glad that you helped him $key"
    }

    override fun getCategories(): LinkedHashMap<String, Boolean> {
        return unicornProperties
    }

    override fun toString(): String {
        return javaClass.simpleName
    }
}
