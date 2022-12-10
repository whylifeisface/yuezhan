package com.example.myapplication.perfence

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : PreferenceFragmentCompat() {
    val one: EditTextPreference? = findPreference("edit_text_preference_1")

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//        TODO("Not yet implemented")
        setPreferencesFromResource(R.xml.perference,rootKey)
    }

}