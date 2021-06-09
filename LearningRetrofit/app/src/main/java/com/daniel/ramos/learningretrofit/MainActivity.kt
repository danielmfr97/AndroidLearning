package com.daniel.ramos.learningretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.daniel.ramos.learningretrofit.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService: AlbumService
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        setContentView(binding.root)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)
    }

    override fun onStart() {
        super.onStart()
//        getRequestWithQueryParameters()
//        getRequestWithPathParameters()
        uploadAlbum()
    }



    private fun getRequestWithQueryParameters() {
        val responseLiveData: LiveData<Response<Album>> = liveData {
            val response = retService.getSortedAlbums(3)
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
                    val result = " " + "Album Title : ${albumsItem.title}" + "\n" +
                            " " + "Album id : ${albumsItem.id}" + "\n" +
                            " " + "User id : ${albumsItem.userId}" + "\n\n\n"
                    binding.textView.append(result)
                }
            }
        })
    }

    private fun getRequestWithPathParameters() {
        // path parameter example
        val pathResponse: LiveData<Response<AlbumItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }
        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
        })
    }

    private fun uploadAlbum() {
        val album = AlbumItem(0, "My Title", 3)
        val postResponse: LiveData<Response<AlbumItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)
        }

        postResponse.observe(this, Observer {
            val receivedAlbumItem = it.body()
            val result = " " + "Album Title : ${receivedAlbumItem?.title}" + "\n" +
                    " " + "Album id : ${receivedAlbumItem?.id}" + "\n" +
                    " " + "User id : ${receivedAlbumItem?.userId}" + "\n\n\n"
            binding.textView.text = result
        })
    }
}