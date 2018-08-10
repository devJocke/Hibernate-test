package Data

import javax.persistence.*

@Entity
@Table(name = "Farm", schema = "dbo", catalog = "mobileRemoteDb")
class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var unicornId: Int = 0
    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "blueprintId")
    var bluePrintId: Blueprints? = null

}
