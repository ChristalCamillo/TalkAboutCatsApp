package br.com.zup.talkaboutcats.ui.register.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zup.talkaboutcats.databinding.ActivityRegisterBinding
import br.com.zup.talkaboutcats.domain.model.User
import br.com.zup.talkaboutcats.ui.home.view.HomeActivity
import br.com.zup.talkaboutcats.ui.register.viewmodel.RegisterViewModel
import br.com.zup.talkaboutcats.utils.REGISTER
import br.com.zup.talkaboutcats.utils.USER_KEY

import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = REGISTER

        binding.btnCadastreLogin.setOnClickListener {
            val user = getDataUser()
            viewModel.registerUser(user)
        }

        initObservers()
    }

    private fun getDataUser(): User {
        return User(
            name = binding.etNome.text.toString(),
            email = binding.etEmail.text.toString(),
            password = binding.etPassword.text.toString()
        )
    }

    private fun initObservers() {
        viewModel.registerState.observe(this) {
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