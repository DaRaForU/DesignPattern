package kh.edu.rupp.ite.completedapi.view.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.completedapi.view.ui.fragment.HomeFragment
import kh.edu.rupp.ite.completedapi.view.ui.fragment.ProvinceFragment
import kh.edu.rupp.ite.completedapi.R
import kh.edu.rupp.ite.completedapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        //Show Default Home Fragment
        showFragment(HomeFragment());

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mnuHome -> showFragment(HomeFragment());
                R.id.mnuProvince -> showFragment(ProvinceFragment());
            }

            true
        };

    }

    private fun showFragment(fragment: Fragment){
        //Fragment Manager
        val fragmentManager = supportFragmentManager;

        //Fragment Transaction
        val fragmentTransaction = fragmentManager.beginTransaction();

        //Replace lytFrame by any fragment
        fragmentTransaction.replace(R.id.lytFrame, fragment);

        //Fragment Commit
        fragmentTransaction.commit();
    }
}