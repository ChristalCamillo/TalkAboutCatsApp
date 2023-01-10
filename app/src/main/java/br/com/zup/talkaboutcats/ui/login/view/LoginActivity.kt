package br.com.zup.talkaboutcats.ui.login.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zup.talkaboutcats.databinding.ActivityLoginBinding
import br.com.zup.talkaboutcats.domain.model.User
import br.com.zup.talkaboutcats.ui.home.view.HomeActivity
import br.com.zup.talkaboutcats.ui.login.viewmodel.LoginViewModel
import br.com.zup.talkaboutcats.ui.register.view.RegisterActivity
import br.com.zup.talkaboutcats.utils.USER_KEY
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCadastreLogin.setOnClickListener {
            goToRegister()
        }

        binding.btnEntrar.setOnClickListener {
            val user = getDataUser()
            viewModel.validateDataUser(user)
        }
        initObservers()
    }

    override fun onStart() {
        super.onStart()
        val actualUser = viewModel.getUser()
        actualUser?.reload()
    }

    private fun getDataUser(): User {
        return User(
            email = binding.etEmail.text.toString(),
            password = binding.etPassword.text.toString()
        )
    }

    private fun goToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun initObservers() {
        viewModel.loginState.observe(this) {
            goToHome(it)
        }

        viewModel.errorState.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun goToHome(user: User) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(USER_KEY, user)
        }
        startActivity(intent)
    }
}