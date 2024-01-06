package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = viewModel(MainViewModel::class.java) as MainViewModel
        val adapter = Adapter(object : DetailsItemUi {
            override fun detailsItemUi(id: Long) {
                DetailsBottomSheetDialogFragment(id).show(
                    supportFragmentManager,
                    "CreateDetailsBottomSheetFragment"
                )
            }
        })
        binding.recyclerView.adapter = adapter

        viewModel.liveData().observe(this) {
            adapter.update(it)
        }

        binding.addButton.setOnClickListener {
            AddBottomSheetDialogFragment().show(
                supportFragmentManager,
                "CreateAddBottomSheetFragmentDialog"
            )
        }

        viewModel.init()
    }

    override fun viewModel(clasz: Class<out ViewModel>): ViewModel =
        (application as ProvideViewModel).viewModel(clasz)

}