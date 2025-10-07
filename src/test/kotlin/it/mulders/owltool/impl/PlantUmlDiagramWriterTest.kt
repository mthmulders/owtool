package it.mulders.owltool.impl

import assertk.assertThat
import assertk.assertions.contains
import it.mulders.owltool.EXAMPLE_NAMESPACE
import it.mulders.owltool.model.Class
import it.mulders.owltool.model.Ontology
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream

class PlantUmlDiagramWriterTest {
    private val diagramWriter = PlantUmlDiagramWriter()

    @Test
    fun `should write PlantUML header`() {
        // Arrange
        val ontology = Ontology(setOf())

        // Act
        val diagram = ontology.generateDiagram()

        // Assert
        assertThat(diagram).contains("@startuml")
    }

    @Test
    fun `should write PlantUML footer`() {
        // Arrange
        val ontology = Ontology(setOf())

        // Act
        val diagram = ontology.generateDiagram()

        // Assert
        assertThat(diagram).contains("@enduml")
    }

    @Test
    fun `should write single class`() {
        // Arrange
        val ontology = Ontology(setOf(Class(EXAMPLE_NAMESPACE, "Single")))

        // Act
        val diagram = ontology.generateDiagram()

        // Assert
        assertThat(diagram).contains("class Single {")
    }

    @Test
    fun `should write specialisation of class`() {
        // Arrange
        val ontology = Ontology(
            setOf(
                Class(EXAMPLE_NAMESPACE, "Parent", setOf(
                    Class(EXAMPLE_NAMESPACE, "Child")
            ))))

        // Act
        val diagram = ontology.generateDiagram()

        // Assert
        assertThat(diagram).contains("Parent <|-- Child")
    }

    private fun Ontology.generateDiagram(): String = ByteArrayOutputStream().use { stream ->
        diagramWriter.generateDiagram(this, stream)
        return stream.toString()
    }
}