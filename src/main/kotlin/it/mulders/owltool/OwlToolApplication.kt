package it.mulders.owltool

import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
import it.mulders.owltool.cli.DiagramCommand
import jakarta.inject.Inject
import picocli.CommandLine

@CommandLine.Command(
    mixinStandardHelpOptions = true,
    name = "owltool",
    subcommands = [DiagramCommand::class],
)
@QuarkusMain
class OwlToolApplication
    @Inject
    constructor(
        val factory: CommandLine.IFactory,
    ) : QuarkusApplication {
        override fun run(vararg args: String): Int = CommandLine(this, factory).execute(*args)
    }
