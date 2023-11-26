package ru.easycode.zerotoheroandroidtdd

interface Count {

    fun initial(number: String): UiState

    fun increment(number: String): UiState

    fun decrement(number: String): UiState

    class Base(private val step: Int, private val max: Int, private val min: Int) : Count {

        init {
            if (step < 1) {
                throw IllegalStateException("step should be positive, but was $step")
            }
            if (max < 1) {
                throw IllegalStateException("max should be positive, but was $max")
            }
            if (max <= step) {
                throw IllegalStateException("max should be more than step")
            }
            if (max <= min) {
                throw IllegalStateException("max should be more than min")
            }
        }

        override fun initial(number: String): UiState {
            return when (number.toInt()) {
                max -> UiState.Max(number)

                min -> UiState.Min(number)

                else -> UiState.Base(number)
            }
        }

        override fun increment(number: String): UiState {
            val result = number.toInt() + step
            if ((step + result) > max) {
                return UiState.Max(result.toString())
            }
            return UiState.Base(result.toString())
        }

        override fun decrement(number: String): UiState {
            val result = number.toInt() - step
            if ((result - step) < min) {
                return UiState.Min(result.toString())
            }
            return UiState.Base(result.toString())
        }
    }
}