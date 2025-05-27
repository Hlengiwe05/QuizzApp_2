package vcmsa.hlengiwe.quizzappassi2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class QuizApp : AppCompatActivity() {
    //decs
    //Arrays of Questions and Answers
    private val questions = arrayOf(
        "Nazi Party came to power in 1933?",
        "The Berlin Wall collapsed in 1942?",
        "World War II began in 1932?",
        "Adolf Hitler died in 1945?",
        "In 1945 Germany was defeated in the World War II?"
    )
    private val answerChoices = arrayOf(
        arrayOf("A: False", "B: True"),
        arrayOf("A: False", "B: True"),
        arrayOf("A: False", "B: True"),
        arrayOf("A: False", "B: True"),
        arrayOf("A: False", "B: True")
    )
    private val userAnswers = mutableListOf<Boolean>()
    private val correctAnswers = arrayOf(true, false, false, true, true)



        @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_quiz_app)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }//end of ViewCompat
            //Code goes here
            //link the elements from the GUI to the backend
            val tvQuestions = findViewById<TextView>(R.id.tvQuestions)
            val rbtngAnswers = findViewById<RadioGroup>(R.id.rbtngAnswers)
            val btnNext = findViewById<Button>(R.id.btnProceed)
            val username = intent.getStringExtra("username")

            var currentQuestionIndex = 0
            var score = 0

            // Function to display the current question and its answer choices
            //tvQuestions.text = questions[currentQuestionIndex]
            fun displayQuestion() {
                tvQuestions.text = questions[currentQuestionIndex]
                /*
                if (currentQuestionIndex < questions.size) {
                    tvQuestions.text = questions[currentQuestionIndex]

                // Ensure rbtngAnswers has enough RadioButtons for the choices
                if (rbtngAnswers.childCount >= answerChoices[currentQuestionIndex].size) {
                    for (i in 0 until answerChoices[currentQuestionIndex].size) {
                        val radioButton = rbtngAnswers.getChildAt(i) as? RadioButton // Safe cast
                        radioButton?.text = answerChoices[currentQuestionIndex][i]
                    }
                } else {
                    // Handle case where there aren't enough RadioButtons (e.g., log error, show Toast)
                    Toast.makeText(this, "Error: Not enough RadioButtons for choices.", Toast.LENGTH_LONG).show()

                    }

                 */
                    rbtngAnswers.clearCheck()
                }
            displayQuestion()




                btnNext.setOnClickListener {
                    if (currentQuestionIndex < questions.size) {
                        tvQuestions.text = questions[currentQuestionIndex]
                        // Ensure rbtngAnswers has enough RadioButtons for the choices
                        val selectedId = rbtngAnswers.checkedRadioButtonId

                        if (selectedId == -1) {
                            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        }
                        val selectedAnswer = when (selectedId) {
                            R.id.rbtnB -> true
                            R.id.rbtnA -> false
                            else -> false
                        }

                        userAnswers.add(selectedAnswer)

                        if (selectedAnswer == correctAnswers[currentQuestionIndex]) {
                            score++
                        }

                        currentQuestionIndex++

                        if (currentQuestionIndex < questions.size) {
                            displayQuestion()


                    } else {
                        // Quiz finished, go to QuizApp3
                        val intent = Intent(this, QuizApp3::class.java)
                        var score = 0
                        for (i in userAnswers.indices) {
                            if (userAnswers[i] == correctAnswers[i]) {
                                score++
                            }

                            intent.putExtra("score", score)
                            intent.putExtra("username", username)
                        }
                        startActivity(intent)
                        finish()

                    }//end of QuizActivity

                }
            }
    }
}
