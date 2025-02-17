package com.practicum.moviesearchapp.ui.root

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    private lateinit var confirmDialog: MaterialAlertDialogBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        confirmDialog = MaterialAlertDialogBuilder(this)
            .setTitle("Вы действительно хотите выйти?")
            .setNegativeButton("Нет") { dialog, which ->

            }.setPositiveButton("Да") { dialog, which ->
                finish()
            }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.rootFragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.detailsFragment, R.id.moviesCastFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                confirmDialog.show()
            }

        })
    }

}