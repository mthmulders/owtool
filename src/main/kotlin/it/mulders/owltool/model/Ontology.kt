package it.mulders.owltool.model

data class Ontology(
    val classes: Collection<Class>,
) {
    fun classCount(): Int = classes.countRecursively()

    private fun Collection<Class>.countRecursively(): Int = this.asSequence().map { it.children.countRecursively() + 1 }.sum()
}
