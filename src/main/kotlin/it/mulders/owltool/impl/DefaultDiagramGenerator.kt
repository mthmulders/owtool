package it.mulders.owltool.impl

import it.mulders.owltool.DiagramGenerator
import it.mulders.owltool.DiagramWriter
import it.mulders.owltool.OntologyLoader
import jakarta.enterprise.context.ApplicationScoped
import org.slf4j.LoggerFactory
import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream

@ApplicationScoped
class DefaultDiagramGenerator(val loader: OntologyLoader, val writer: DiagramWriter) : DiagramGenerator {
    override fun generateDiagram(
        path: Path,
        namespace: String
    ): Result<Path> {
        log.info("Generating diagram for $path")

        val ontology = loader.load(path.inputStream(), namespace)
        log.info("Ontology loaded, found ${ontology.classCount()} classes in namespace $namespace")

        val outputPath = determineOutputPath(path)
        writer.generateDiagram(ontology, outputPath.outputStream())

        return Result.success(outputPath)
    }

    private fun determineOutputPath(inputPath: Path): Path {
        val extension = inputPath.extension
        val newFileName = inputPath.fileName.toString().replace(extension, "puml")
        return inputPath.parent.resolve(newFileName)
    }

    companion object {
        private val log = LoggerFactory.getLogger(DefaultDiagramGenerator::class.java)
    }
}