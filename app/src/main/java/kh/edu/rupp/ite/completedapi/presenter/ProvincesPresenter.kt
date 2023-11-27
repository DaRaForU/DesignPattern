package kh.edu.rupp.ite.completedapi.presenter

import android.widget.Toast
import kh.edu.rupp.ite.completedapi.api.model.Province
import kh.edu.rupp.ite.completedapi.api.service.ApiService
import kh.edu.rupp.ite.completedapi.view.ProvincesView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvincesPresenter(private val view: ProvincesView) {

    fun loadProvinces(){
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
                        view.showProvinceList(response.body());
                    }else{
                        view.showError("Error while load data from server!");
                    }
                }

                override fun onFailure(call: Call<List<Province>>, t: Throwable) {
                    view.showError("Error while load data from server!");
                }

            }
        );
    }

    fun onProvinceClick(province: Province){
        view.showProvinceDetails(province);
    }
}