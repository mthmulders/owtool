package it.mulders.owltool.cli

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import picocli.CommandLine
import kotlin.jvm.java

@CommandLine.Command(
    name = "diagram",
    mixinStandardHelpOptions = true,
    description = ["Generate a diagram of the ontology"],
)
class DiagramCommand : Runnable {
    @CommandLine.Parameters(
        arity = "1",
        description = ["Path to the ontology source"],
        paramLabel = "input",
    )
    lateinit var input: String

    override fun run() {
        log.info("Generating a diagram for $input")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(DiagramCommand::class.java)
    }
}
