package com.mazecube.myapplication

import androidx.lifecycle.*
import com.mazecube.myapplication.data.Resource
import com.mazecube.myapplication.data.model.Facts
import com.mazecube.myapplication.data.source.FactsRepository
import com.mazecube.myapplication.utill.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    factsRepository: FactsRepository
    ) : ViewModel() {

    private val list = arrayListOf<Facts>()

    private val _event = MutableLiveData<Event<Unit>>()
    val data = _event.switchMap {
        factsRepository.getData()
    }

    private val _dataList = MutableLiveData<List<Facts>>().apply {
        value = list.toList()
    }
    val dataList : LiveData<List<Facts>> = _dataList

    fun removeData(item: Facts) {
        list.remove(item).run {
            _dataList.value = list.toList()
        }
    }

    fun clickEvent() {
        _event.value = Event(Unit)
    }

    fun addData(item : Facts) {
        list.add(list.size, item).apply {
            _dataList.value = list.toList()
        }
    }

}