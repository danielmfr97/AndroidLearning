package br.com.daniel.ramos.acompanhamentoodonto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import br.com.daniel.ramos.acompanhamentoodonto.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarCards()
    }

    private fun configurarCards() {
        binding.cvCartilha.setOnClickListener(this)
        binding.cvCovid.setOnClickListener(this)
        binding.cvAgostoDourado.setOnClickListener(this)
        binding.cvQuem.setOnClickListener(this)
        binding.cvQuiz.setOnClickListener(this)
        binding.cvDestque.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        lateinit var argument_bundle: String
        when (view) {
            binding.cvCartilha -> argument_bundle = "cartilha_screen"
            binding.cvCovid -> argument_bundle = "covid_screen"
            binding.cvAgostoDourado -> argument_bundle = "agosto_screen"
            binding.cvQuem -> argument_bundle = "quem_screen"
            binding.cvQuiz -> argument_bundle = "quiz_screen"
            binding.cvDestque -> argument_bundle = "destaque_screen"
        }
        //TODO: Redirecionar para segunda tela com bundle
        val bundle = bundleOf("screen_type" to argument_bundle)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}