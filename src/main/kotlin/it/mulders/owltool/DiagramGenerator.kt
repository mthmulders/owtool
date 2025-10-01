package it.mulders.owltool

import java.nio.file.Path

fun interface DiagramGenerator {
    fun generateDiagram(
        path: Path,
        namespace: String,
    ): Result<Path>
}
