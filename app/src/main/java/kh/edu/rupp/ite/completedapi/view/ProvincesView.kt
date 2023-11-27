package kh.edu.rupp.ite.completedapi.view

import kh.edu.rupp.ite.completedapi.api.model.Province

interface ProvincesView {
    fun showProvinceList(provinces: List<Province>?);

    fun showError(message: String);

    fun showProvinceDetails(province: Province);
}