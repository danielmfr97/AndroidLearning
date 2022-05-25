package br.com.daniel.ramos.acompanhamentoodonto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.daniel.ramos.acompanhamentoodonto.databinding.FragmentMotionrBinding
import br.com.daniel.ramos.acompanhamentoodonto.model.SwipeRightModel
import kotlinx.coroutines.delay

class MotionFragment : Fragment() {

    private var _binding: FragmentMotionrBinding? = null
    private val binding get() = _binding!!
    val viewModel: MotionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMotionrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularQuiz()
        configureViews()
        configureObservers()
    }

    private fun  popularQuiz() {
        viewModel.popularQuiz()
    }

    private fun configureViews() {
        binding.motionLayout.setTransitionListener(object : TransitionAdapter() {

            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.offScreenPass -> {
                        motionLayout.progress = 0f
                        motionLayout.setTransition(R.id.rest, R.id.like)
                        viewModel.swipe(false)
                    }
                    R.id.offScreenLike -> {
                        motionLayout.progress = 0f
                        motionLayout.setTransition(R.id.rest, R.id.like)
                        viewModel.swipe(true)
                    }
                }
            }
        })

        binding.btnTrueTwo.setOnClickListener {
            binding.motionLayout.transitionToState(R.id.like)
            Log.d("TESTE", "configureViews: TESTE")
        }

        binding.btnFalseTwo.setOnClickListener {
            binding.motionLayout.transitionToState(R.id.pass)
        }
    }

    private fun configureObservers() {
        viewModel.modelStream.observe(viewLifecycleOwner) {
            bindCard(it)
        }
    }

    private fun bindCard(model: SwipeRightModel) {
        binding.textoPergunta.text = model.bottom.pergunta
        binding.textoPergunta2.text = model.top.pergunta
    }
}
