package org.example

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun divideOrZero(a: Int, b: Int): Int = if (b == 0) 0 else a / b

class Supplier<out T>
class Consumer<in T>

var initCount = 0
var initCount3 = 0

class DelegateOwner {
    val item by lazy2 {
        initCount++
        10 + 2
    }
    val item2 by lazy2 {
        "2" + "10"
    }
    val item3 by lazy2 {
        initCount3++
        null
    }
}

class LazyDelegate<T>(private val initializer: () -> T) : ReadOnlyProperty<Any?, T> {
    private var value: T? = null
    private var initialized = false

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (!initialized) {
            value = initializer()
            initialized = true
        }
        return value as T
    }
}

fun <T> lazy2(initializer: () -> T): LazyDelegate<T> = LazyDelegate(initializer)

fun main() {
    if (divideOrZero(10, 2) != 5) {
        error("Incorrect division")
    }
    if (divideOrZero(10, 0) != 0) {
        error("Incorrect division for zero")
    }

    val strSupp: Supplier<String> = Supplier<String>()
    val anySupp: Supplier<Any> = strSupp

    val anyConsumer: Consumer<Any> = Consumer<Any>()
    val strConsumer: Consumer<String> = anyConsumer

    val owner = DelegateOwner()
    if (initCount != 0) {
        error("Not lazy init")
    }
    val res = owner.item
    if (res != 12) {
        error("Not correct res")
    }
    val res2 = owner.item
    if (res2 != 12) {
        error("Not correct res2")
    }
    if (initCount > 1) {
        error("Too much inits")
    }

    val res3 = owner.item3
    if (res3 != null) {
        error("Not correct res3")
    }

    val res32 = owner.item3
    if (res32 != null) {
        error("Not correct res32")
    }
    if (initCount3 > 1) {
        error("Too much inits")
    }
}