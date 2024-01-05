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

        val adapter = Adapter(object : DeleteItemUi {
            override fun delete(id: Long) {
                DeleteBottomSheetFragment(id).show(
                    supportFragmentManager,
                    "CreateDeleteBottomSheetFragment"
                )
            }
        })

        binding.recyclerView.adapter = adapter
        val viewModel = viewModel(MainViewModel::class.java) as MainViewModel

        viewModel.liveData().observe(this) {
            adapter.update(it)
        }

        binding.addButton.setOnClickListener {
            AddBottomSheetFragment().show(supportFragmentManager, "CreateAddBottomSheetFragment")
        }

        viewModel.init()
    }

    override fun viewModel(clasz: Class<out ViewModel>): ViewModel =
        (application as App).viewModel(clasz)
}