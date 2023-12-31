package com.example.quizgamev2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quizgamev2.R
import com.example.quizgamev2.viewmodel.AuthViewModel
import com.google.android.material.textfield.TextInputEditText

class SignUpFragment : Fragment() {

    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    lateinit var navController: NavController
    lateinit var email: TextInputEditText
    lateinit var password: TextInputEditText
    lateinit var btn_sign_up: Button
    lateinit var btn_back: ImageButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        email=view.findViewById(R.id.txt_email)
        password=view.findViewById(R.id.txt_password)
        btn_sign_up=view.findViewById(R.id.btn_sign_up)
        btn_back=view.findViewById(R.id.btn_back)

        authViewModel.currentUserLiveData.observe(viewLifecycleOwner) {
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        authViewModel.errorMessageLiveData.observe(viewLifecycleOwner) { errorMessage ->
            // Display error message if login fails
            Toast.makeText(requireContext() , errorMessage , Toast.LENGTH_SHORT).show()
        }

        addEventOnViewCreated()
    }

    private fun addEventOnViewCreated() {
        btnSignUp()
        btnBack()
    }

    private fun btnBack() {
        btn_back.setOnClickListener(){
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    private fun btnSignUp() {
        btn_sign_up.setOnClickListener{
            val email = email.text.toString().trim()
            val password = password.text.toString().trim()
            if(email.isNotEmpty() && password.isNotEmpty()){
                authViewModel.signUp(email, password)
            }
        }
    }

}