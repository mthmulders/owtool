package it.mulders.owltool.impl

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isInstanceOf
import it.mulders.owltool.model.Ontology
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class DefaultOntologyLoaderTest {
    private val loader = DefaultOntologyLoader()

    @Test
    fun `should return an instance of Ontology`() {
        // Arrange
        val input = ByteArrayInputStream(ByteArray(0))

        // Act
        val result = loader.load(input, EXAMPLE_NAMESPACE)

        // Assert
        assertThat(result).isInstanceOf(Ontology::class.java)
    }

    @Test
    fun `should load root classes from input`() {
        // Arrange
        val input = DefaultOntologyLoaderTest::class.java.getResourceAsStream("/ontologies/simple.ttl")

        // Act
        val result = loader.load(input, EXAMPLE_NAMESPACE)

        // Assert
        assertThat(result.classes).hasSize(1)
    }

    @Test
    fun `should discover parent-child relations between classes`() {
        // Arrange
        val input = DefaultOntologyLoaderTest::class.java.getResourceAsStream("/ontologies/simple.ttl")

        // Act
        val result = loader.load(input, EXAMPLE_NAMESPACE)

        // Assert
        val patientClass = result.classes.single { it.name == "Person" }
        assertThat(patientClass.children).hasSize(1)
    }

    companion object {
        private const val EXAMPLE_NAMESPACE = "http://purl.org/net/ns/ex#"
    }
}