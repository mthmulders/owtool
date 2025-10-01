package it.mulders.owltool

import it.mulders.owltool.model.Ontology
import java.io.OutputStream

fun interface DiagramWriter {
    fun generateDiagram(
        ontology: Ontology,
        output: OutputStream,
    )
}
