package ru.spbau.mit.hackathon.paywell.authorization

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import org.json.JSONObject
import ru.spbau.mit.hackathon.paywell.R
import ru.spbau.mit.hackathon.paywell.projectslist.MainActivity
import java.io.File

class AuthorizationActivity: AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpLink: TextView
    private lateinit var rememberMe: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization_view);

        val userCredentials = receiveCredentials()
        println(userCredentials)
        if (userCredentials != null && userCredentials.loginByDefault) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            email = findViewById(R.id.inputEmail)
            password = findViewById(R.id.inputPassword)
            loginButton = findViewById(R.id.loginButton)
            signUpLink = findViewById(R.id.signupLink)
            rememberMe = findViewById(R.id.rememberMe)

            loginButton.setOnClickListener {
                if (login()) {
                    if (rememberMe.isActivated) {
                        markChoiceInPreferences()
                    }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            signUpLink.setOnClickListener {
                val intent = Intent(this, RegistrationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun markChoiceInPreferences() {
        val directory = applicationContext.filesDir
        val file = File(directory, getString(R.string.users_credentials_file))
        val credentialsJson = JSONObject(file.readText())
        val remadeCredentials = JSONObject()
        remadeCredentials.put("e-mail", credentialsJson.getString("e-mail"))
        remadeCredentials.put("password", credentialsJson.getString("password"))
        remadeCredentials.put("remember", true)
    }

    private fun login(): Boolean {
        if (!validateCredentials()) {
            return false
        }

        if (rememberMe.isChecked) {
            markChoiceInPreferences()
        }

        loginButton.isEnabled = true

        Handler().postDelayed({
            onLoginSuccess()
        }, 3000)
        return true
    }

    private fun validateCredentials(): Boolean {
        var isValid = true

        val emailText = email.text.toString()
        val passwordText = password.text.toString()

        if (emailText.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.error = "Неправильный адрес e-mail"
            return false
        }

        val directory = applicationContext.filesDir
        val file = File(directory, getString(R.string.users_credentials_file))
        if (!file.exists()) {
            email.error = "Не существует пользователя с данным E-mail"
            return false
        }

        val credentialsAsJson = JSONObject(file.readText())
        if (emailText != credentialsAsJson.getString("e-mail")) {
            email.error = "Не существует пользователя с данным E-mail"
            isValid = false
        }

        if (passwordText != credentialsAsJson.getString("password")) {
            password.error = "Не существует пользователя с данным паролем"
            isValid = false
        }

        return isValid
    }

    private fun onLoginSuccess() {
        loginButton.isEnabled = true
        finish()
    }

    private fun receiveCredentials(): UserCredentials? {
        val directory = applicationContext.filesDir
        val file = File(directory, getString(R.string.users_credentials_file))
        if (file.exists()) {
            val credentialsAsJson = JSONObject(file.readText())
            println(file.readText())
            return UserCredentials(credentialsAsJson.getString("e-mail"), credentialsAsJson.getString("password"), credentialsAsJson.getBoolean("remember"))
        }
        return null
    }
}