package bugs

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import java.util.UUID

@Entity
class OtherTestEntity(
    @Id val id: UUID = UUID.randomUUID(),

    @OneToOne(mappedBy = "otherTestEntity")
    var testEntity: TestEntity? = null,
)
