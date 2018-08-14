package Dao

import javax.persistence.*
import java.util.Objects

@Entity
@Table(name = "Unicorn", schema = "dbo", catalog = "mobileRemoteDb")
class Unicorn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var firstName: String? = null
    var lastName: String? = null
    var thirdName: String? = null

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "unicornCareId")
    var care: Care? = null

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "farmId")
    var farm: Farm? = null

    constructor()

    private constructor(firstName: String, lastName: String, thirdName: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.thirdName = thirdName
        care = Care().newUnicorn()
        farm = Farm()
    }

    class UnicornBuilder(private val firstName: String, private val lastName: String, private val thirdName: String) {
        private val careId: Int = 0

        fun build(): Unicorn {
            return Unicorn(firstName, lastName, thirdName)
        }
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Unicorn) return false
        val unicorn = other as Unicorn?
        return id == unicorn!!.id &&
                firstName == unicorn.firstName &&
                lastName == unicorn.lastName &&
                thirdName == unicorn.thirdName
    }

    override fun hashCode(): Int {
        return Objects.hash(id, firstName, lastName, thirdName)
    }

    override fun toString(): String {
        return firstName + ' '.toString() +
                lastName + ' '.toString() +
                thirdName
    }
}
