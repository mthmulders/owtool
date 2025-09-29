package it.mulders.owltool.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class OntologyTest {
    @Test
    fun `should recursively count classes`() {
        // Arrange
        val roots = setOf(
            Class(EXAMPLE_NAMESPACE, "root-1", setOf(
                Class(EXAMPLE_NAMESPACE, "root-1-a", setOf(
                    Class(EXAMPLE_NAMESPACE, "root-1-a-i"),
                    Class(EXAMPLE_NAMESPACE, "root-1-a-ii")
                )),
                Class(EXAMPLE_NAMESPACE, "root-1-b"),
                Class(EXAMPLE_NAMESPACE, "root-1-c")
            )),
            Class(EXAMPLE_NAMESPACE, "root-2", setOf(
                Class(EXAMPLE_NAMESPACE, "root-2-a")
            )),
            Class(EXAMPLE_NAMESPACE, "root-3")
        )
        val ontology = Ontology(roots)

        // Act
        val result = ontology.classCount()

        // Assert
        assertThat(result).isEqualTo(9)
    }

    companion object {
        private const val EXAMPLE_NAMESPACE = "http://purl.org/net/ns/ex#"
    }
}