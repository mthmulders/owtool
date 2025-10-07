package it.mulders.owltool.impl

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import it.mulders.owltool.EXAMPLE_NAMESPACE
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.nio.file.Path
import kotlin.io.path.Path

class DefaultDiagramGeneratorTest {
    private val loader = DefaultOntologyLoader()
    private val writer = PlantUmlDiagramWriter()

    @Test
    fun `should generate diagram from input`() {
        // Arrange
        val baos = ByteArrayOutputStream()
        val generator = object : DefaultDiagramGenerator(loader, writer) {
            override fun determineOutputStream(path: Path) =  baos
        }

        // Act
        generator.generateDiagram(INPUT_PATH, EXAMPLE_NAMESPACE)

        // Assert
        val content = String(baos.toByteArray())
        assertThat(content).all {
            contains("@startuml")
            contains("@enduml")
        }
    }

    @Test
    fun `should calculate diagram output path from input path`() {
        // Arrange
        val generator = DefaultDiagramGenerator(loader, writer)

        // Act
        val result = generator.generateDiagram(INPUT_PATH, EXAMPLE_NAMESPACE)

        // Assert
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(Path("target", "test-classes", "ontologies", "simple.puml"))
    }

    companion object {
        private val INPUT_PATH = Path("target", "test-classes", "ontologies", "simple.ttl")
    }
}