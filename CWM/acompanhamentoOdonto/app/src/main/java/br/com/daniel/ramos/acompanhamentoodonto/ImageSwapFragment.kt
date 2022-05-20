package br.com.daniel.ramos.acompanhamentoodonto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.daniel.ramos.acompanhamentoodonto.databinding.FragmentImageSwapBinding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ImageSwapFragment : Fragment() {

    private var _binding: FragmentImageSwapBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageSwapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarImagensSlider()
    }

    private fun configurarImagensSlider() {
        val screen_type = arguments?.getString("screen_type")
        val array = when (screen_type) {
            "cartilha_screen" -> getImagesForCartliha()
            else -> {}
        }
        popularSlider(array as ArrayList<SlideModel>)
    }

    private fun getImagesForCartliha(): ArrayList<SlideModel> {
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.folheto_01, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.folheto_02, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.folheto_03, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.folheto_04, ScaleTypes.FIT))

        return imageList
    }

    private fun popularSlider(list: ArrayList<SlideModel>) {
        binding.imageSlider.setImageList(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}