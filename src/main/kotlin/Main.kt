package org.example

import org.example.list.SingleLinkedList.Companion.singleLinkedListOf
import org.example.list.CustomArrayList.Companion.customArrayListOf
import org.example.stack.ArrayListStack.Companion.arrayListStackOf
import org.example.stack.SingleLinkedStack.Companion.singleLinkedStackOf

fun main() {
    val sll = singleLinkedListOf(1, 2, 3)
    val cal = customArrayListOf(4, 5, 6)
    val als = arrayListStackOf(7, 8, 9)
    val sls = singleLinkedStackOf(10, 11, 12)
    ListPrinter.printList(sll)
    ListPrinter.printList(cal)
    ListPrinter.printList(als)
    ListPrinter.printList(sls)
}
