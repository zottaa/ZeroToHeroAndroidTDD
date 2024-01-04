package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = viewModel(ContainerViewModel::class.java) as ContainerViewModel

        viewModel.liveData().observe(this) {
            it.show(supportFragmentManager, binding.root.id)
        }

        viewModel.init(savedInstanceState == null)
    }

    override fun viewModel(clasz: Class<out ViewModel>): ViewModel {
        return (application as App).viewModel(clasz)
    }
}