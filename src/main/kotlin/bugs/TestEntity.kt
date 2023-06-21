package bugs

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Version
import java.util.UUID

@Entity
class TestEntity(
    @Id val id: UUID = UUID.randomUUID(),

    // Removing this field will make the test pass
    @Version
    var version: Long? = null,

    @OneToOne
    @JoinColumn(name = "other_test_id")
    var otherTestEntity: OtherTestEntity? = null,
)
