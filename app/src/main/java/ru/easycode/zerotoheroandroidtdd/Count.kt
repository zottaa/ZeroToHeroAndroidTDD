package ru.easycode.zerotoheroandroidtdd

interface Count {
    fun increment(number: String): UiState

    class Base(private val step: Int, private val max: Int) : Count {
        init {
            if (step < 1) {
                throw IllegalStateException("step should be positive, but was $step")
            }
            if (max < 1) {
                throw IllegalStateException("max should be positive, but was $max")
            }
            if (step > max) {
                throw IllegalStateException("max should be more than step")
            }
        }

        override fun increment(number: String): UiState {
            val result = number.toInt() + step
            if ((result + step) > max) {
                return UiState.Max(result.toString())
            }
            return UiState.Base(result.toString())
        }

    }
}