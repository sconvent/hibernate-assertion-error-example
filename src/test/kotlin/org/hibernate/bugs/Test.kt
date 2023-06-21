package org.hibernate.bugs

import bugs.OtherTestEntity
import bugs.TestEntity
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class Test {

    private lateinit var entityManagerFactory: EntityManagerFactory

    @BeforeEach
    fun init() {
        entityManagerFactory = Persistence.createEntityManagerFactory( "test" )
    }

    @Test
    fun insert() {
        val entityManagerFactory = entityManagerFactory.createEntityManager()

        entityManagerFactory.transaction.begin()
        val otherTestEntity = OtherTestEntity()
        entityManagerFactory.merge(otherTestEntity) // Changing this to a persist(...) call will make the test pass
        entityManagerFactory.transaction.commit()

        entityManagerFactory.transaction.begin()
        val testEntity = TestEntity(otherTestEntity = otherTestEntity)
        entityManagerFactory.persist(testEntity)
        entityManagerFactory.transaction.commit()

        entityManagerFactory.close()
    }
}
