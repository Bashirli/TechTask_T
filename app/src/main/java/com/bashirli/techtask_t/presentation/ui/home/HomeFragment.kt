package com.bashirli.techtask_t.presentation.ui.home


import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bashirli.techtask_t.R
import com.bashirli.techtask_t.common.base.BaseFragment
import com.bashirli.techtask_t.common.utils.gone
import com.bashirli.techtask_t.common.utils.visible
import com.bashirli.techtask_t.databinding.FragmentHomeBinding
import com.bashirli.techtask_t.presentation.ui.home.adapters.CityFilterAdapter
import com.bashirli.techtask_t.presentation.ui.home.adapters.CountryFilterAdapter
import com.bashirli.techtask_t.presentation.ui.home.adapters.PeopleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by viewModels()

    private val peopleAdapter = PeopleAdapter()
    private val countryFilterAdapter = CountryFilterAdapter()
    private val cityFilterAdapter = CityFilterAdapter()
    private var isLocalEmpty = true


    override fun observeEvents() {
        with(binding) {
            with(viewModel) {
                liveData.observe(viewLifecycleOwner) {
                    when (it) {
                        is HomeUiState.CountryData -> {
                            if (it.data.isEmpty() && !isLocalEmpty) {
                                getAllLocalData()
                            } else if (it.data.isNotEmpty()) {
                                viewModel.insertAllData(it.data)
                            } else {
                                progressBar.gone()
                                rvPeople.gone()
                                viewModel.setEffect(HomeUiEffect.ShowMessage(resources.getString(R.string.nothing_to_show)))
                            }
                        }

                        HomeUiState.Error -> {
                            progressBar.gone()
                            rvPeople.gone()
                            if (!isLocalEmpty) {
                                viewModel.getAllLocalData()
                            } else {
                                viewModel.setEffect(HomeUiEffect.ShowMessage(resources.getString(R.string.nothing_to_show)))
                            }
                        }

                        HomeUiState.Loading -> {
                            progressBar.visible()
                            rvPeople.gone()
                        }

                        is HomeUiState.PeopleData -> {
                            if (it.data.isEmpty()) {
                                viewModel.getAllCountryData()
                            } else {
                                progressBar.gone()
                                rvPeople.visible()
                                peopleAdapter.submitData(it.data)
                            }
                        }

                        is HomeUiState.InsertionStatus -> {
                            if (it.isSuccess) {
                                viewModel.getAllLocalData()
                            } else {
                                viewModel.getAllCountryData()
                            }
                        }

                        is HomeUiState.IsLocalEmpty -> isLocalEmpty = it.isEmpty

                        is HomeUiState.CitiesData -> {
                            progressBar.gone()
                            rvPeople.visible()
                            cityFilterAdapter.submitData(it.data)
                        }

                        is HomeUiState.EntityCountriesData -> {
                            countryFilterAdapter.submitData(it.data)
                        }
                    }
                }

                effect.observe(viewLifecycleOwner) {
                    when (it) {
                        is HomeUiEffect.ShowMessage -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                countrySelectedIdList.observe(viewLifecycleOwner) {
                    countryFilterAdapter.submitData(countryList)
                }

                citySelectedIdList.observe(viewLifecycleOwner) {
                    cityFilterAdapter.submitData(cityList)
                }

            }
        }
    }

    override fun onViewCreateFinish() {
        setup()
    }

    private fun setup() {
        setRV()
        with(binding) {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getAllCountryData()
                swipeRefreshLayout.isRefreshing = false
            }
            buttonFilterCity.setOnClickListener {
                if (layoutCountryFilter.isVisible) {
                    layoutCountryFilter.gone()
                }
                layoutCityFilter.visible()
            }

            buttonCityFilterApply.setOnClickListener {
                if (!viewModel.citySelectedIdList.value.isNullOrEmpty()) {
                    viewModel.getFilteredPeople()
                } else {
                    viewModel.setEffect(HomeUiEffect.ShowMessage(resources.getString(R.string.error_select)))
                }
                layoutCityFilter.gone()
            }

            buttonFilterCountry.setOnClickListener {
                if (layoutCityFilter.isVisible) {
                    layoutCityFilter.gone()
                }
                layoutCountryFilter.visible()
            }

            buttonCountryFilterApply.setOnClickListener {
                if (!viewModel.countrySelectedIdList.value.isNullOrEmpty()) {
                    viewModel.getFilteredCities()
                } else {
                    viewModel.setEffect(HomeUiEffect.ShowMessage(resources.getString(R.string.error_select)))
                }
                layoutCountryFilter.gone()
            }

        }
    }

    private fun setRV() {
        with(binding) {

            rvPeople.adapter = peopleAdapter
            rvCountry.adapter = countryFilterAdapter
            rvCity.adapter = cityFilterAdapter

            rvCountry.itemAnimator = null
            rvCity.itemAnimator = null

            countryFilterAdapter.onClickItemListener = {
                viewModel.setCountryId(it)
            }
            cityFilterAdapter.onClickItemListener = {
                viewModel.setCityId(it)
            }
        }
    }

}