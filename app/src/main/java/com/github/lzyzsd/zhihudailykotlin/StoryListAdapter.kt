package com.github.lzyzsd.zhihudailykotlin

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.util.*

/**
 * Created by bruce on 7/21/15.
 */
public class StoryListAdapter(context: Context) : RecyclerView.Adapter<StoryListAdapter.ViewHolder>() {

    var stories: List<Story> = ArrayList<Story>()
    private val typedValue = TypedValue()
    private val background: Int

    public class ViewHolder(public val view: View) : RecyclerView.ViewHolder(view) {
        public val imageView: ImageView
        public val titleTextView: TextView

        init {
            imageView = view.findViewById(R.id.avatar) as ImageView
            titleTextView = view.findViewById(android.R.id.text1) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + titleTextView.getText()
        }
    }

    init {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true)
        background = typedValue.resourceId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false)
        view.setBackgroundResource(background)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val story = stories.get(position)
        holder.titleTextView.setText(story.title)

        holder.view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val context = v.getContext()
                val intent = Intent(context, javaClass<StoryDetailActivity>())
                intent.putExtra(StoryDetailActivity.EXTRA_NAME, stories.get(holder.getAdapterPosition()))

                context.startActivity(intent)
            }
        })

        Glide.with(holder.imageView.getContext()).load(story.images!![0]!!).fitCenter().into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return stories.size()
    }
}