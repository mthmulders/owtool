package it.mulders.owltool.model

data class Class(
    val namespace: String,
    val name: String,
    val children: Collection<Class> = emptySet(),
) {
    fun withChildren(children: Collection<Class>) = Class(namespace, name, children)
}
