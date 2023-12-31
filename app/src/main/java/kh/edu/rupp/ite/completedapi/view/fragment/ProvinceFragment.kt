package kh.edu.rupp.ite.completedapi.view.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.completedapi.api.model.Province
import kh.edu.rupp.ite.completedapi.api.service.ApiService
import kh.edu.rupp.ite.completedapi.databinding.FragmentProvinceBinding
import kh.edu.rupp.ite.completedapi.model.api.model.Status
import kh.edu.rupp.ite.completedapi.presenter.ProvincesPresenter
import kh.edu.rupp.ite.completedapi.view.ProvincesView
import kh.edu.rupp.ite.completedapi.view.ui.adapter.ProvinceAdapter
import kh.edu.rupp.ite.completedapi.viewmodel.ProvincesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvinceFragment: Fragment(){
    private lateinit var binding: FragmentProvinceBinding;

    private val viewModel = ProvincesViewModel();

    private val adapter = ProvinceAdapter();

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

//        Set Up Recyclerview
        //// create layout manager
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        //// create adapter
        adapter.onProvinceClickListener = { index, province ->


//            Toast.makeText(context, "Province: $index, ${province.name}", Toast.LENGTH_LONG).show();
        }

        binding.recyclerView.adapter = adapter;

        viewModel.loadProvinces();

        //Setup observe
        viewModel.provinceData.observe(viewLifecycleOwner){
            when(it.status){
                Status.PROCESSING -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show();
                Status.SUCCESS -> adapter.submitList(it.data);
                Status.ERROR -> Toast.makeText(requireContext(), "Error while loading data from server", Toast.LENGTH_LONG).show();
            }

//            if(it.status == Status.ERROR){
//                Toast.makeText(requireContext(), "Error while loading data from server", Toast.LENGTH_LONG).show();
//            }else if(it.status == Status.SUCCESS){
//                adapter.submitList(it.data)
//            }
        }

    }


//    private fun loadProvinceListFromServer(){
//
//
//    }

//    private fun showProvinceList(provinceList: List<Province>?){
////        adapter.submitList(provinceList);
////        binding.recyclerView.adapter = adapter;
//    }
}