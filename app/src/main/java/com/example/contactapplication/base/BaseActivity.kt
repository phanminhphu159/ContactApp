package com.example.contactapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelLazy
import androidx.viewbinding.ViewBinding
import com.example.contactapplication.ktext.boolean.isNotTrue
import com.example.contactapplication.ktext.boolean.isTrue
import com.example.contactapplication.widget.ProgressDialog
import com.example.contactapplication.base.viewmodel.BaseViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<viewModel : BaseViewModel, viewBinding : ViewBinding>(viewModelClass: KClass<viewModel>) :
    AppCompatActivity() {

    protected val viewModel by ViewModelLazy(
        viewModelClass,
        { viewModelStore },
        { defaultViewModelProviderFactory }
    )
    protected lateinit var viewBinding: viewBinding
    abstract fun inflateViewBinding(inflater: LayoutInflater): viewBinding

    protected var progressDialog: ProgressDialog? = null

    protected abstract fun initialize()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = inflateViewBinding(layoutInflater)
        progressDialog = ProgressDialog(this)
        setContentView(viewBinding.root)
        initialize()
    }

    fun showLoading(isShow: Boolean) {
        if (isShow && progressDialog?.isShowing.isNotTrue()) {
            progressDialog?.show()
        } else if (progressDialog?.isShowing.isTrue()) {
            progressDialog?.dismiss()
        }
    }

}
