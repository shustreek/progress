package com.amachikhin.testapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val timer = object : CountDownTimer(10_090L, 100L) {
        override fun onFinish() {
            distance.setProgress(100F)
            distance.setValue("100%")

            progress.progress = 100
        }

        override fun onTick(p0: Long) {
            val scale = 100 - p0 / 100
            distance.setProgress(scale.toFloat())
            distance.setValue("${scale.toInt()}%")

            progress.progress = scale.toInt()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_first.setOnClickListener {
            //            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            timer.cancel()
            timer.start()
        }


    }
}
