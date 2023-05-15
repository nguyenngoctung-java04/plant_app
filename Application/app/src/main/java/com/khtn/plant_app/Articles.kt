package com.khtn.plant_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khtn.plant_app.databinding.FragmentArticlesBinding
import com.khtn.plant_app.databinding.FragmentHomeBinding

class Articles : Fragment() {
    private lateinit var binding: FragmentArticlesBinding
    private lateinit var adapter: AdapterRecycleView
    private lateinit var recycleView: RecyclerView
    private lateinit var articlesArrayList: ArrayList<ArticlesData>

    lateinit var image_url : Array<String>
    lateinit var title : Array<String>
    lateinit var articles: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticlesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        InitData()
        val layoutManager = LinearLayoutManager(context)
        recycleView = binding.recycleViewArticles
        recycleView.layoutManager = layoutManager
        recycleView.setHasFixedSize(true)
        adapter = AdapterRecycleView(articlesArrayList)
        recycleView.adapter = adapter
    }

    private fun InitData()
    {
        articlesArrayList = arrayListOf<ArticlesData>()

        image_url = arrayOf(
            "https://haycafe.vn/wp-content/uploads/2022/07/Hinh-anh-cay-tao.jpg",
            "https://haycafe.vn/wp-content/uploads/2022/07/Hinh-anh-cay-tao.jpg",
            "https://haycafe.vn/wp-content/uploads/2022/07/Hinh-anh-cay-tao.jpg"
        )

        title = arrayOf(
            "David Austin, Who Breathed Life Into the Rose, Is Dead at 92",
            "David Austin, Who Breathed Life Into the Rose, Is Dead at 92",
            "David Austin, Who Breathed Life Into the Rose, Is Dead at 92"
        )

        for (i in image_url.indices)
        {
            val articles = ArticlesData(title[i], image_url[i])
            articlesArrayList.add(articles)
        }

    }

}