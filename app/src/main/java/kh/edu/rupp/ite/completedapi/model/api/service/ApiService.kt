package kh.edu.rupp.ite.completedapi.api.service

import kh.edu.rupp.ite.completedapi.api.model.Province
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/provinces.json")
    fun loadProvinceList(): Call<List<Province>>;
}