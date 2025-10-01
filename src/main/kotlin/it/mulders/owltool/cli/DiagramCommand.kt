package it.mulders.owltool.cli

import it.mulders.owltool.DiagramGenerator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import picocli.CommandLine
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.jvm.java

@CommandLine.Command(
    name = "diagram",
    mixinStandardHelpOptions = true,
    description = ["Generate a diagram of the ontology in a namespace"],
)
class DiagramCommand(
    private val generator: DiagramGenerator,
) : Runnable {
    @CommandLine.Parameters(
        arity = "1",
        description = ["Path to the ontology source"],
        paramLabel = "input",
    )
    lateinit var input: String

    @CommandLine.Parameters(
        arity = "1",
        description = ["Namespace for which to generate a diagram"],
        paramLabel = "namespace",
    )
    lateinit var namespace: String

    override fun run() {
        val path = Path(System.getProperty("user.dir"), input)

        if (!namespace.endsWith("#")) namespace += "#"

        if (!Files.exists(path)) {
            log.error("The specified input file [{}] does not exist", path)
            return
        }

        log.info("Generating diagram; input={}, namespace={}", path, namespace)

        val result = generator.generateDiagram(path, namespace)
        result.map { log.info("Generated diagram in {}", it.toAbsolutePath()) }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(DiagramCommand::class.java)
    }
}
