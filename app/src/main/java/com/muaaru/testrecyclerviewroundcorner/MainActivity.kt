package com.muaaru.testrecyclerviewroundcorner

import android.content.Context
import android.graphics.Outline
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.muaaru.testrecyclerviewroundcorner.databinding.ActivityMainBinding
import com.muaaru.testrecyclerviewroundcorner.databinding.ItemHeaderBinding
import com.muaaru.testrecyclerviewroundcorner.databinding.ItemTextBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var groupAdapter: GroupAdapter<ViewHolder<*>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupRecyclerViewAdapter()
        binding.recyclerView.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            outlineProvider = ClipOutlineProvider()
            clipToOutline = true
        }
    }

    private fun setupRecyclerViewAdapter(){
        groupAdapter = GroupAdapter<ViewHolder<*>>().apply {
            update(listOf(
                HeaderItem("Header1"),
                TextItem("test1"),
                TextItem("test2"),
                TextItem("test3"),
                TextItem("test4"),
                TextItem("test5"),
                TextItem("test6"),
                HeaderItem("Header2"),
                TextItem("testA"),
                TextItem("testB"),
                TextItem("testC"),
                TextItem("testD"),
                TextItem("testE")
            ))
        }
    }

    class HeaderItem(private val text: String) : BindableItem<ItemHeaderBinding>() {
        override fun getLayout() = R.layout.item_header
        override fun bind(viewBinding: ItemHeaderBinding, position: Int) {
            viewBinding.textView.text = text
        }
    }

    class TextItem(private val text: String) : BindableItem<ItemTextBinding>() {
        override fun getLayout() = R.layout.item_text
        override fun bind(viewBinding: ItemTextBinding, position: Int) {
            viewBinding.textView.text = text
        }
    }

    class ClipOutlineProvider : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            val margin = dp2Px(10f, view.context).toInt()
            outline.setRoundRect(
                margin, margin, (view.width - margin), (view.height - margin),
                dp2Px(30f, view.context)
            )
        }

        private fun dp2Px(dp: Float, context: Context): Float {
            val metrics = context.resources.displayMetrics
            return dp * metrics.density
        }
    }
}
