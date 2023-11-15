package kh.edu.rupp.ite.completedapi.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.completedapi.api.model.Province
import kh.edu.rupp.ite.completedapi.api.service.ApiService
import kh.edu.rupp.ite.completedapi.databinding.FragmentProvinceBinding
import kh.edu.rupp.ite.completedapi.view.ui.adapter.ProvinceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvinceFragment: Fragment() {
    private lateinit var binding: FragmentProvinceBinding;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProvinceBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadProvinceListFromServer();
    }

    private fun loadProvinceListFromServer(){

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
                    Toast.makeText(context, "Yes", Toast.LENGTH_LONG).show();
                    if(response.isSuccessful){
                        showProvinceList(response.body());
                    }else{
                        Toast.makeText(context, "no in Yes", Toast.LENGTH_LONG).show();
                    }
                }

                override fun onFailure(call: Call<List<Province>>, t: Throwable) {
                    Toast.makeText(context, "No", Toast.LENGTH_LONG).show();
                }

            }
        );
    }

    private fun showProvinceList(provinceList: List<Province>?){
        //create layout manager
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        //create adapter
        val adapter = ProvinceAdapter();
        adapter.submitList(provinceList);
        binding.recyclerView.adapter = adapter;
    }
}