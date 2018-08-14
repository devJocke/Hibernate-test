package Dao

import javax.persistence.*
import javax.persistence.FetchType
import FarmOverview

@Entity
@Table(name = "Farm", schema = "dbo", catalog = "mobileRemoteDb")
class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id = 0

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER )
    var blueprints = mutableListOf<Blueprints>()


    fun getFarmOverView() {
        FarmOverview(this)
    }
}