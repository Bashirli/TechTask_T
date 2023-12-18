package com.bashirli.techtask_t.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bashirli.techtask_t.common.base.BaseViewModel
import com.bashirli.techtask_t.common.base.Effect
import com.bashirli.techtask_t.common.base.State
import com.bashirli.techtask_t.domain.model.local.CityEntityUiModel
import com.bashirli.techtask_t.domain.model.local.CountryEntityUiModel
import com.bashirli.techtask_t.domain.model.local.EntityUiModel
import com.bashirli.techtask_t.domain.model.local.PeopleEntityUiModel
import com.bashirli.techtask_t.domain.useCase.local.RoomUseCase
import com.bashirli.techtask_t.domain.useCase.remote.GetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val localUseCase: RoomUseCase,
) : BaseViewModel<HomeUiState, HomeUiEffect>() {

    private val _countrySelectedIdList = MutableLiveData<ArrayList<Int>>()
    val countrySelectedIdList: LiveData<ArrayList<Int>> = _countrySelectedIdList

    private val _citySelectedIdList = MutableLiveData<ArrayList<Int>>()
    val citySelectedIdList: LiveData<ArrayList<Int>> get() = _citySelectedIdList

    val countryList = ArrayList<CountryEntityUiModel>()

    val cityList = ArrayList<CityEntityUiModel>()

    init {
        getAllLocalData()
    }

    fun getAllCountryData() {
        viewModelScope.launch {
            getDataUseCase.getData().handleResult(
                onComplete = {
                    setState(HomeUiState.CountryData(it))
                },
                onLoading = {
                    setState(HomeUiState.Loading)
                },
                onError = {
                    setState(HomeUiState.Error)
                    setEffect(HomeUiEffect.ShowMessage(it.localizedMessage as String))
                }
            )
        }
    }

    fun insertAllData(countryList: List<EntityUiModel>) {
        viewModelScope.launch {
            localUseCase.insertAllData(countryList).also {
                when (it) {
                    1 -> {
                        setState(HomeUiState.InsertionStatus(true))
                    }

                    else -> {
                        setState(HomeUiState.InsertionStatus(false))
                    }
                }
            }
        }
    }

    fun getCountryData() {
        viewModelScope.launch {
            localUseCase.getCountriesData().handleResult(
                onComplete = {
                    setState(HomeUiState.EntityCountriesData(it))
                    countryList.clear()
                    countryList.addAll(it)
                    _countrySelectedIdList.value = ArrayList(it.map { it.countryId })
                },
                onLoading = {
                    setState(HomeUiState.Loading)
                },
                onError = {
                    setState(HomeUiState.Error)
                    setEffect(HomeUiEffect.ShowMessage(it.localizedMessage as String))
                }
            )
        }
    }

    fun getCityData() {
        viewModelScope.launch {
            localUseCase.getCitiesData().handleResult(
                onComplete = {
                    setState(HomeUiState.CitiesData(it))
                    cityList.clear()
                    cityList.addAll(it)
                    _citySelectedIdList.value = ArrayList(it.map { it.cityId })
                },
                onLoading = {
                    setState(HomeUiState.Loading)
                },
                onError = {
                    setState(HomeUiState.Error)
                    setEffect(HomeUiEffect.ShowMessage(it.localizedMessage as String))
                }
            )
        }
    }

    fun getAllLocalData() {
        getPeopleData()
        getCountryData()
        getCityData()
    }

    fun getPeopleData() {
        viewModelScope.launch {
            localUseCase.getPeopleData().handleResult(
                onComplete = {
                    setState(HomeUiState.PeopleData(it))
                    setState(HomeUiState.IsLocalEmpty(it.isEmpty()))
                },
                onLoading = {
                    setState(HomeUiState.Loading)
                },
                onError = {
                    setState(HomeUiState.Error)
                    setEffect(HomeUiEffect.ShowMessage(it.localizedMessage as String))
                }
            )
        }
    }

    fun getFilteredCities() {
        viewModelScope.launch {
            countrySelectedIdList.value?.let { filter ->
                if (filter.isNotEmpty()) {
                    localUseCase.getFilteredCities(filter).handleResult(
                        onComplete = {
                            setState(HomeUiState.CitiesData(it))
                            cityList.clear()
                            cityList.addAll(it)
                            _citySelectedIdList.value = ArrayList(it.map { it.cityId })
                            getFilteredPeople()
                        },
                        onLoading = {
                            setState(HomeUiState.Loading)
                        },
                        onError = {
                            setState(HomeUiState.Error)
                            setEffect(HomeUiEffect.ShowMessage(it.localizedMessage as String))
                        }
                    )
                }
            }
        }
    }

    fun getFilteredPeople() {
        viewModelScope.launch {
            citySelectedIdList.value?.let { filter ->
                if (filter.isNotEmpty()) {
                    localUseCase.getFilteredPeople(filter).handleResult(
                        onComplete = {
                            setState(HomeUiState.PeopleData(it))
                        },
                        onLoading = {
                            setState(HomeUiState.Loading)
                        },
                        onError = {
                            setState(HomeUiState.Error)
                            setEffect(HomeUiEffect.ShowMessage(it.localizedMessage as String))
                        }
                    )
                }
            }
        }
    }


    //listOperation
    fun setCountryId(item: CountryEntityUiModel) {
        val list = countrySelectedIdList.value
        val id = item.countryId
        list?.let { myList ->
            if (myList.any { it == id }) {
                myList.remove(id)
            } else {
                myList.add(id)
            }
            countryList[countryList.indexOf(item)] = item.copy(isSelected = !item.isSelected)
        }
        _countrySelectedIdList.value = list ?: arrayListOf()
    }

    fun setCityId(item: CityEntityUiModel) {
        val list = citySelectedIdList.value
        val id = item.cityId
        list?.let { myList ->
            if (myList.any { it == id }) {
                myList.remove(id)
            } else {
                myList.add(id)
            }
            cityList[cityList.indexOf(item)] = item.copy(isSelected = !item.isSelected)
        }
        _citySelectedIdList.value = list ?: arrayListOf()
    }


}

sealed interface HomeUiState : State {
    data object Error : HomeUiState

    data object Loading : HomeUiState

    data class IsLocalEmpty(val isEmpty: Boolean) : HomeUiState

    data class PeopleData(val data: List<PeopleEntityUiModel>) : HomeUiState

    data class EntityCountriesData(val data: List<CountryEntityUiModel>) : HomeUiState

    data class CitiesData(val data: List<CityEntityUiModel>) : HomeUiState

    data class CountryData(val data: List<EntityUiModel>) : HomeUiState

    data class InsertionStatus(val isSuccess: Boolean) : HomeUiState

}

sealed interface HomeUiEffect : Effect {
    data class ShowMessage(val message: String) : HomeUiEffect
}