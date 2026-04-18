package org.example.list

class CustomArrayList(initSize: Int = 2) : CustomList {
    var inner = IntArray(initSize)
    override var size = 0
        private set

    private fun resize(newSize: Int): IntArray {
        return inner.copyOf(newSize)
    }

    private fun checkIndex(index: Int) {
        if (index !in 0 until size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
    }

    private fun ensureCapacity() {
        if (inner.isEmpty()) {
            inner = resize(1)
        }
        if (size == inner.size) {
            inner = resize(inner.size * 2)
        }
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return inner[index]
    }

    override fun set(index: Int, value: Int) {
        checkIndex(index)
        inner[index] = value
    }

    override fun add(element: Int) {
        ensureCapacity()
        inner[size] = element
        size++
    }

    override fun addFirst(element: Int) {
        ensureCapacity()
        for (i in size downTo 1) {
            inner[i] = inner[i - 1]
        }
        inner[0] = element
        size++
    }

    override fun remove(element: Int): Boolean {
        val index = indexOf(element)
        if (index == -1) return false

        size--
        for (i in index until size) {
            inner[i] = inner[i + 1]
        }
        return true
    }

    fun removeLast(): Boolean {
        if (size == 0) throw NoSuchElementException("No elements")
        set(size - 1, 0)
        size--
        return true
    }

    override fun indexOf(element: Int): Int {
        for (i in 0 until size) {
            if (element == inner[i]) return i
        }
        return -1
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            private var current = 0

            override fun hasNext(): Boolean = current < size
            override fun next(): Int {
                if (!hasNext()) throw NoSuchElementException()
                return inner[current++]
            }
        }
    }

    companion object {
        fun customArrayListOf(vararg items: Int) = items.fold(CustomArrayList(items.size)) { list, item ->
            list.also { it.add(item) }
        }
    }
}