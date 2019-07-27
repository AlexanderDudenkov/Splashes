package com.dudencovgmail.splashes.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.dudencovgmail.splashes.R
import com.github.ajalt.timberkt.Timber.d
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

/*//<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
fun Context.isOnline(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    return if (netInfo != null && netInfo.isConnectedOrConnecting) {
        true
    } else {
        toast("Check internet connection")
        false
    }
}*/

fun Context.toast(message: String?) {
    message?.let {
        if (it.isNotEmpty())
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }
}

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, tag: String) {
    supportFragmentManager.transact {
        add(fragment, tag)
    }
}

fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run {
        action()
    }
}

/*fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, injector.mainViewModelFactory()).get(viewModelClass)

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, this.activity!!.injector.mainViewModelFactory()).get(viewModelClass)*/

/*fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactoryOld.getInstance()).get(viewModelClass)

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactoryOld.getInstance()).get(viewModelClass)*/

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun AppCompatActivity?.startFragment(fragment: Fragment,
                                     @IdRes containerViewId: Int,
                                     enterAnimRes: Int = R.anim.no_anim,
                                     exitAnimRes: Int = R.anim.no_anim,
                                     enterPopAnimRes: Int = enterAnimRes,
                                     exitPopAnimRes: Int = exitAnimRes) {

    this?.supportFragmentManager?.findFragmentByTag(key(fragment))
            ?: this?.supportFragmentManager?.beginTransaction()
                    ?.setCustomAnimations(enterAnimRes, exitAnimRes, enterPopAnimRes, exitPopAnimRes)
                    ?.replace(containerViewId, fragment)
                    ?.addToBackStack(null)
                    ?.commit()
}

fun AppCompatActivity?.startFragmentAnim(fragment: Fragment,
                                         @IdRes containerViewId: Int) {

    this?.startFragment(fragment,
            containerViewId,
            R.anim.enter_from_right,
            R.anim.exit_to_right,
            R.anim.enter_from_right,
            R.anim.exit_to_right)
}

fun Activity.key(fragment: Fragment): String {
    val key = fragment::class.java.simpleName
    d { "Activity.key: $key" }
    return key
}

fun Activity?.hideKeyBoard(): Boolean {
    val view = this?.currentFocus
    if (view != null) {
        (this?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        return true
    }
    return false
}

fun Activity?.nextActivity(activityIntent: Intent) {
    this?.startActivity(activityIntent)
    this?.finish()
}

fun ViewGroup.inflate(layoutRes: Int) = inflater().inflate(layoutRes, this, false)

fun ViewGroup.inflater() = LayoutInflater.from(this.context)

fun Fragment.key(): String {
    val key = Fragment::class.java.simpleName
    d { "Fragment.key: $key" }
    return key
}

fun ImageView.loadImage(url: String?) {
    if (!TextUtils.isEmpty(url)) {
        Picasso.get()
                .load(url)
                .noPlaceholder()
                .into(this)
    }
}

fun View?.setBackgroundColorExt(colorResId: Int) {
    this?.setBackgroundColor(this.getColor(colorResId)!!)
}

fun View?.setBackgroundColorExt(colorHex: String) {
    this?.setBackgroundColorExt(Color.parseColor(colorHex))
}

fun View?.getColor(colorResId: Int) = this?.let { ContextCompat.getColor(it.context, colorResId) }

fun ProgressBar?.showProgress(activity: Activity?, inProgress: Boolean) {
    this?.let {
        activity?.hideKeyBoard()
        Observable.just(inProgress)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { state ->
                    if (state)
                        it.visibility = View.VISIBLE
                    else
                        it.visibility = View.GONE
                }
    }
}

