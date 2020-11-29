package com.callor.rxnetwork

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.callor.rxnetwork.ui.RxBaseFragment
import com.callor.rxnetwork.vm.MovieManager
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : RxBaseFragment() {

    private val movieManager by lazy { MovieManager() } // (1) 늦은 초기화 선언

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

//        if (rv_movie_list.adapter == null) {
//            rv_movie_list.adapter = MovieAdapter()
//        }
    }

    private fun requestMovie() {
        val subscription = movieManager.getMovieList()
                .subscribeOn(Schedulers.io()) // (3)
                .subscribe ( // 여기서는 데이터의 소비가 일어난다.
                        { retrievedMovie -> // (4) onNext()의 처리
                            // (rv_movie_list.adapter as MovieAdapter).addMovieList(retrievedMovie)
                        },
                        { e -> // (5) onError()의 처리
                            //Snackbar.make(rv_movie_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                            // rv_movie_list.snackbar(e.message ?: "") // Anko의 스낵바
                        }
                )
    }


}