package kh.edu.rupp.ite.completedapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.completedapi.api.model.Province
import kh.edu.rupp.ite.completedapi.api.service.ApiService
import kh.edu.rupp.ite.completedapi.model.api.model.ApiData
import kh.edu.rupp.ite.completedapi.model.api.model.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvincesViewModel: ViewModel() {


//    private val provincesData = MutableLiveData<List<Province>>(); "Bad Practice"

//    Bast Practice
    private val _provincesData = MutableLiveData<ApiData<List<Province>>>();
    val provinceData: LiveData<ApiData<List<Province>>>
        get() = _provincesData


    fun loadProvinces(){
        val apiData = ApiData<List<Province>>(Status.PROCESSING, null);
        _provincesData.postValue(apiData);

        //create retrofit client
        val httpClient = Retrofit.Builder()
            .baseUrl("https://tests3bk.s3.ap-southeast-1.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        //create Service Object
        val apiService = httpClient.create(ApiService::class.java);

        //load province list from server
        val task: Call<List<Province>> = apiService.loadProvinceList();
        task.enqueue(
            object : Callback<List<Province>> {
                override fun onResponse(
                    call: Call<List<Province>>,
                    response: Response<List<Province>>
                ) {

                    if(response.isSuccessful){
                        val apiData = ApiData<List<Province>>(Status.SUCCESS, response.body());
//                        val apiData = ApiData(Status.SUCCESS, response.body());
                        _provincesData.postValue(apiData);
                    }else{
                        val apiData = ApiData<List<Province>>(Status.ERROR, null);
                        _provincesData.postValue(apiData);
                    }
                }

                override fun onFailure(call: Call<List<Province>>, t: Throwable) {
                    val apiData = ApiData<List<Province>>(Status.ERROR, null);
                    _provincesData.postValue(apiData);
                }

            }
        );

    }
}