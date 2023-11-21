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
import kh.edu.rupp.ite.completedapi.presenter.ProvincesPresenter
import kh.edu.rupp.ite.completedapi.view.ProvincesView
import kh.edu.rupp.ite.completedapi.view.ui.adapter.ProvinceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvinceFragment: Fragment(), ProvincesView {
    private lateinit var binding: FragmentProvinceBinding;
    private lateinit var presenter: ProvincesPresenter;

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

        presenter = ProvincesPresenter(this);

//        Set Up Recyclerview
        //// create layout manager
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        //// create adapter
        adapter.onProvinceClickListener = { index, province ->

            presenter.onProvinceClick(province);
//            Toast.makeText(context, "Province: $index, ${province.name}", Toast.LENGTH_LONG).show();
        }

        binding.recyclerView.adapter = adapter;

        presenter.loadProvinces();

    }

    override fun showProvinceList(provinces: List<Province>?) {
        if(provinces == null || provinces.isEmpty()){
            Toast.makeText(context, "No Provinces data", Toast.LENGTH_LONG).show();
        }else{
            adapter.submitList(provinces)
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    override fun showProvinceDetails(province: Province) {
        val dialog = AlertDialog.Builder(requireContext());
        dialog.setMessage(province.name);
        dialog.setPositiveButton("OK"
        ) { p0, p1 -> p0.dismiss() }
        dialog.show();
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