package com.app.testflowmerge

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

class MainViewModel : ViewModel() {


    private fun createListFlow(count: Int): List<Flow<Int>> {

        val list = arrayListOf<Flow<Int>>()

        repeat(count) {

            list.add(
                flow {
                    delay((it + 1) * 100L)
                    emit(it + 1)
                }
            )

        }
        return list

    }

    fun resultFlow(count: Int): Flow<Int> {
        val list = createListFlow(count)

        var cashValue = 0

        return merge(flows = list.toTypedArray()).map {
            it + cashValue
        }.map {
            cashValue = it
            it
        }

    }

}