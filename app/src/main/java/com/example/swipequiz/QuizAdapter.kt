package com.example.swipequiz
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_quiz.view.*

/**
 * Quiz adapter class
 */
class QuizAdapter(private val quissions: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {
    lateinit var context: Context


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(quiz: Quiz) {
            // Get the true answer when click on quiz
            itemView.tvQuiz.setOnClickListener{
                Snackbar.make(itemView, context.resources.getString(R.string.answer, quiz.answer), Snackbar.LENGTH_SHORT).show()
            }
            itemView.tvQuiz.text = quiz.question

        }
    }
// onCreateViewHolder: Called when RecyclerView needs a new RecyclerView.ViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Initialize the context
        context = parent.context
        return ViewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.item_quiz, parent, false)
        )
    }

    // get array size
    override fun getItemCount(): Int {
        return quissions.size
    }

    //  Called by RecyclerView to bind the data with the specified position.
    //  The bind method made in the previous step is used.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(quissions[position])
    }
}
