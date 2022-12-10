package com.example.myapplication.ui.Page

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.myapplication.R

class Perference:PreferenceFragmentCompat() {
    val one: EditTextPreference? = findPreference("edit_text_preference_1")

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//        TODO("Not yet implemented")
        setPreferencesFromResource(R.xml.perference,rootKey)
    }
}