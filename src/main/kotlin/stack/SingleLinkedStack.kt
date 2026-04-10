package org.example.stack

import org.example.list.SingleLinkedList

class SingleLinkedStack() : SingleLinkedList(), Stack {
    override fun push(value: Int) {
        addFirst(value)
    }

    override fun pop(): Int {
        if (isEmpty) throw NoSuchElementException("Stack is empty")
        return removeFirst()     // O(1)
    }

    override fun peek(): Int {
        if (isEmpty) throw NoSuchElementException("Stack is empty")
        return get(0)            // O(1)
    }

    override val isEmpty: Boolean get() = size == 0

    companion object {
        fun singleLinkedStackOf(vararg items: Int) =
            items.fold(SingleLinkedStack()) { list, item ->
                list.also { it.add(item) }
            }
    }
}