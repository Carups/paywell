package ru.spbau.mit.hackathon.paywell.authorization

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import ru.spbau.mit.hackathon.paywell.R
import ru.spbau.mit.hackathon.paywell.projectslist.MainActivity
import java.io.File

class RegistrationActivity: AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var passwordAgain: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);

        email = findViewById(R.id.regEmail)
        password = findViewById(R.id.regPassword)
        passwordAgain = findViewById(R.id.regPasswordAgain)
        registerButton = findViewById(R.id.regButton)

        registerButton.setOnClickListener {
            if (register()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun register(): Boolean {
        if (!validateCredentials()) {
            return false
        }

        registerButton.isEnabled = true

        Handler().postDelayed({
            onRegisterSuccess()
        }, 3000)
        return true
    }

    private fun validateCredentials(): Boolean {
        var isValid = true

        val emailText = email.text.toString()
        val passwordText = password.text.toString()
        val repeatedPasswordText = passwordAgain.text.toString()

        if (emailText.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.error = "Неправильный адрес e-mail"
            isValid = false
        }

        if (passwordText.length < 8) {
            password.error = "Пароль должен содержать как минимум 8 символов"
            isValid = false
        }

        if (passwordText != repeatedPasswordText) {
            passwordAgain.error = "Пароли не совпадают"
            isValid = false
        }

        return isValid
    }

    private fun onRegisterSuccess() {
        registerButton.isEnabled = true
        saveCredentials()
        finish()
    }

    private fun saveCredentials() {
        val directory = applicationContext.filesDir
        val file = File(directory, getString(R.string.users_credentials_file))
//        if (!file.exists()) {
            file.createNewFile()
//        }
        val newCredentials = JSONObject()
        newCredentials.put("e-mail", email.text.toString())
        newCredentials.put("password", password.text.toString())
        newCredentials.put("remember", false)
        file.writeText(newCredentials.toString())
    }
}