package org.example

object ListPrinter {
    fun printList(collection: Iterable<Int>) {
        println(collection.joinToString(prefix = "[", postfix = "]"))
    }
}