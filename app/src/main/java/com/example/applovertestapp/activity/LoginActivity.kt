package com.example.applovertestapp.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.applovertestapp.R
import com.example.applovertestapp.databinding.ActivityLoginBinding
import com.example.applovertestapp.viewModel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()
    private var binding: ActivityLoginBinding? = null

    private var shouldExitOnNectBackPress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

        viewModel.getIsLoginStartedLiveData().observe(this, Observer {
            showProgressDialog(it)
        })

        viewModel.getErrorsLiveDate().observe(this, Observer {
            showError(it)
        })

        viewModel.getSuccessLiveData().observe(this, Observer {
            openSuccessActivity()
        })
    }

    override fun onBackPressed() {
        if(shouldExitOnNectBackPress){
            finishAndRemoveTask()
        } else {
            shouldExitOnNectBackPress = true

            Toast.makeText(this, "Click back again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed({ shouldExitOnNectBackPress = false }, 1500 )
        }
    }

    override fun onPause() {
        super.onPause()

        viewModel.clearDisposables()
    }

    override fun onStop() {
        super.onStop()

        viewModel.clearDisposables()
    }

    private fun showError(throwable: Throwable){
        AlertDialog.Builder(this)
            .apply {
                title = "Error"
                setMessage(throwable.message)
                setCancelable(false)
                setNeutralButton("Ok", { dialogInterface, i ->  dialogInterface.dismiss()})
            }.show()
    }

    private fun showProgressDialog(isLoginStarted : Boolean){
        binding?.progressView?.visibility = if(isLoginStarted)View.VISIBLE else View.GONE
    }

    private fun openSuccessActivity(){
        startActivity(Intent(this, SuccessActivity::class.java))
    }
}