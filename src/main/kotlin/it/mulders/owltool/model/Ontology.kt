package it.mulders.owltool.model

data class Ontology(
    val classes: Collection<Class>,
) {
    fun classCount(): Int {
        return classes.countRecursively()
    }

    private fun Collection<Class>.countRecursively(): Int {
        return this.asSequence()
            .map { it.children.countRecursively() + 1 }
            .sum()
    }
}
