package org.example.stack

import org.example.list.CustomArrayList

class ArrayListStack(initSize: Int = 2) : Stack, Iterable<Int> {
    private val storage = CustomArrayList()

    override fun push(value: Int) {
        storage.add(value)
    }

    override fun pop(): Int {
        if (isEmpty) throw NoSuchElementException("Stack is empty")
        val result = storage[size - 1]
        storage.removeLast()
        return result
    }

    override fun peek(): Int {
        if (isEmpty) throw NoSuchElementException("Stack is empty")
        return storage[storage.size - 1]
    }

    override val isEmpty: Boolean get() = storage.size == 0

    // CustomList's methods (delegation)
    override fun get(index: Int): Int = storage[index]
    override fun set(index: Int, value: Int) = storage.set(index, value)
    override fun add(element: Int) = storage.add(element)
    override fun addFirst(element: Int) = storage.addFirst(element)
    override fun remove(element: Int): Boolean = storage.remove(element)
    override fun indexOf(element: Int): Int = storage.indexOf(element)
    override val size: Int get() = storage.size
    override fun iterator(): Iterator<Int> = storage.iterator()

    companion object {
        fun arrayListStackOf(vararg items: Int) = items.fold(ArrayListStack(items.size)) { list, item ->
            list.also { it.add(item) }
        }
    }
}