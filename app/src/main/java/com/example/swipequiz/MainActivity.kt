package com.example.swipequiz

/**
 * @author Raeef Ibrahim
 * 500766393
 * Main Activity
 */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    // get array of quissions
    private val quissions = arrayListOf<Quiz>()
    // set array in adapter
    private val quizAdapter = QuizAdapter(quissions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    // Initialize the recycler view with a linear layout manager, adapter
    private fun initViews() {
        rvQuiz.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuiz.adapter = quizAdapter
        rvQuiz.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        // Loop in quiz and add questions and answers
        for (i in Quiz.QUESTIONS.indices) {
            quissions.add(Quiz(Quiz.QUESTIONS[i], Quiz.ANSWERS[i]))
        }
        createItemTouchHelper().attachToRecyclerView(rvQuiz)

    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return false

            }

            /**
             * Callback triggered when a user swiped an item.
             *  Implement Swipe functionality
             */

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                quizAdapter.notifyItemChanged(viewHolder.adapterPosition)
                // get item position
                val position = viewHolder.adapterPosition
                // find a correct or incorrect answer with the swipe direction
                if ((direction == ItemTouchHelper.LEFT && !quissions[position].answer)
                    || (direction == ItemTouchHelper.RIGHT && quissions[position].answer)){
                    // show the answer is correct
                    Snackbar.make(rvQuiz, getString(R.string.correct), Snackbar.LENGTH_SHORT).show()
                    // remove the correct answer
                    quissions.removeAt(position)
                    // notify data change after item is removed
                        quizAdapter.notifyDataSetChanged()
                    // Give massage if all answers are correct
                    if (quissions.isEmpty()){
                        Snackbar.make(rvQuiz, getString(R.string.done), Snackbar.LENGTH_LONG).show()
                    }
                    return
                } else {
                    Snackbar.make(rvQuiz, getString(R.string.incorrect), Snackbar.LENGTH_SHORT)
                        .show()
                }

            }

        }
        return ItemTouchHelper(callback)

    }
}
