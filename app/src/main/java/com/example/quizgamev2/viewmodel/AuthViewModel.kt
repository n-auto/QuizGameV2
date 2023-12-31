package com.example.quizgamev2.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizgamev2.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class AuthViewModel : ViewModel() {
    var repository = AuthRepository()

    private var _currentUserLiveData = MutableLiveData<FirebaseUser>()
    val currentUserLiveData: LiveData<FirebaseUser> = _currentUserLiveData

    private var _errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    val errorMessageLiveData: LiveData<String> = _errorMessageLiveData

    fun signUp(email: String, password: String) {
        repository.signUp(
            email,
            password,
            onComplete = { user ->
                _currentUserLiveData.postValue(user)
            },
            onError = { errorMessage ->
                _errorMessageLiveData.postValue(errorMessage)
            }
        )
    }

    fun signIn(email: String, password: String) {
        repository.signIn(
            email,
            password,
            onComplete = { user ->
                _currentUserLiveData.postValue(user)
            },
            onError = { errorMessage ->
                _errorMessageLiveData.postValue(errorMessage)
            }
        )
    }

    fun signOut() {
        repository.signOut(
            onComplete = { user ->
                _currentUserLiveData.postValue(user)
            },
            onError = { errorMessage ->
                _errorMessageLiveData.postValue(errorMessage)
            }
        )
    }

    fun signInWithGoogle(idToken: String) {
        repository.signInWithGoogle(
            idToken,
            onComplete = { user ->
                _currentUserLiveData.postValue(user)
            },
            onError = { errorMessage ->
                _errorMessageLiveData.postValue(errorMessage)
            })
    }

    fun sendEmailToResetPassword(email: String) {
        repository.sendEmailToResetPassword(email)
    }
}