package org.example

class SingleLinkedList : CustomList {

    // Node class for the linked list elements
    private class Node(var value: Int, var next: Node? = null)

    private var head: Node? = null
    override var size: Int = 0
        private set

    // Helper function
    private fun getNodeAt(index: Int): Node {
        checkIndex(index)
        var current = head
        repeat(index) {
            current = current?.next
        }
        return current ?: throw IndexOutOfBoundsException()
    }

    // Add at the end
    override fun add(element: Int) {
        if (head == null)
            head = Node(element)
        else {
            var current = head
            while (current?.next != null)
                current = current.next
            current?.next = Node(element)
        }
        size++
    }

    // Add at the beginning
    override fun addFirst(element: Int) {
        head = Node(element, head)
        size++
    }

    // Get the item at the index
    override fun get(index: Int): Int {
        return getNodeAt(index).value
    }

    // Replace the item at the index
    override fun set(index: Int, value: Int) {
        getNodeAt(index).value = value
    }

    // Find the first occurrence of the element
    override fun indexOf(element: Int): Int {
        var current = head
        var index = 0
        while (current != null) {
            if (current.value == element)
                return index
            current = current.next
            index++
        }
        return -1
    }

    // Remove first occurrence of element
    override fun remove(element: Int): Boolean {
        if (head == null)
            return false

        if (head?.value == element) {
            head = head?.next
            size--
            return true
        }

        var current = head
        while (current?.next != null) {
            if (current.next?.value == element) {
                current.next = current.next?.next
                size--
                return true
            }
            current = current.next
        }
        return false
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            private var current = head

            override fun hasNext(): Boolean = current != null

            override fun next(): Int {
                if (!hasNext())
                    throw NoSuchElementException()
                val value = current!!.value
                current = current!!.next
                return value
            }
        }
    }

    // Helper function to validate an index
    private fun checkIndex(index: Int) {
        if (index !in 0 until size)
            throw IndexOutOfBoundsException("Index $index, size $size")
    }

    companion object {
        fun singleLinkedListOf(vararg items: Int) =
            items.fold(SingleLinkedList()) { list, item ->
                list.also{ it.add(item) }
            }
    }
}