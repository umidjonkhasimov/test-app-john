package uz.gita.testappkotlinumidjon.repository

import uz.gita.testappkotlinumidjon.model.TestData

object Repository {
    private var allQuestions: MutableList<TestData> = mutableListOf()

    private var givenQuestion: MutableList<TestData> = mutableListOf()

    private lateinit var answers: MutableList<String?>

    init {
        allQuestions.add(
            TestData(
                "Which word is spelled correctly?",
                "Carro",
                "Kars",
                "Car",
                "Karr",
                "Car"
            )
        )
        allQuestions.add(
            TestData(
                "Which of the following words means the opposite of \"hot\"?",
                "Cold",
                "Warm",
                "Burn",
                "Freezing",
                "Cold"
            )
        )
        allQuestions.add(
            TestData(
                "Which sentence is in the present tense?",
                "I will eat pizza for dinner.",
                "I am eating pizza for dinner.",
                "I ate pizza for dinner.",
                "I had eaten pizza for dinner.",
                "I am eating pizza for dinner."
            )
        )
        allQuestions.add(
            TestData(
                "Which of the following words is a verb?",
                "Blue",
                "Happy",
                "Jump",
                "Desk",
                "Jump"
            )
        )
        allQuestions.add(
            TestData(
                "Which punctuation mark is used to end a question?",
                "Period (.)",
                "Exclamation point (!)",
                "Comma (,)",
                "Question mark (?)",
                "Question mark (?)"
            )
        )
        allQuestions.add(
            TestData(
                "Which word is a synonym for \"big\"?",
                "Small",
                "Large",
                "Short",
                "Tall",
                "Large"
            )
        )
        allQuestions.add(
            TestData(
                "Which of the following is a noun?",
                "Running",
                "Quickly",
                "Dog",
                "Singing",
                "Dog"
            )
        )
        allQuestions.add(
            TestData(
                "Which sentence is in the past tense?",
                "I am eating breakfast.",
                "I will eat breakfast.",
                "I ate breakfast.",
                "I am going to eat breakfast.",
                "I ate breakfast."
            )
        )
        allQuestions.add(
            TestData(
                "Which of the following words means the opposite of \"happy\"?",
                "Sad",
                "Angry",
                "Excited",
                "Joyful",
                "Sad"
            )
        )
        allQuestions.add(
            TestData(
                "Which word is spelled correctly?",
                "Wensday",
                "Wednesday",
                "Wendesday",
                "Wednesdey",
                "Wednesday"
            )
        )
        allQuestions.add(
            TestData(
                "Which of the following words means the opposite of \"slow\"?",
                "Fast",
                "Small",
                "Dark",
                "Loud",
                "Fast"
            )
        )
        allQuestions.add(
            TestData(
                "Which sentence is in the future tense?",
                "I am eating lunch.",
                "I ate lunch.",
                "I will eat lunch.",
                "I had eaten lunch.",
                "I will eat lunch."
            )
        )
        allQuestions.add(
            TestData(
                "Which sentence is in the present perfect tense?",
                "I am eating dinner.",
                "I ate dinner.",
                "I will eat dinner.",
                "I have eaten dinner.",
                "I have eaten dinner."
            )
        )
        allQuestions.add(
            TestData(
                "Which of the following words means the opposite of \"day\"?",
                "Night",
                "Light",
                "Bright",
                "Sky",
                "Night"
            )
        )
        allQuestions.add(
            TestData(
                "Which of the following words means the opposite of \"short\"?",
                "Tall",
                "Small",
                "Wide",
                "Long",
                "Long"
            )
        )
    }

    fun getNQuestions(n: Int): MutableList<TestData> {
        allQuestions.shuffle()
        givenQuestion = allQuestions.subList(0, n)
        return givenQuestion
    }

    fun getGivenQuestions(): MutableList<TestData> {
        return givenQuestion
    }

    fun saveAnswers(answers: MutableList<String?>) {
        this.answers = answers
    }

    fun getAnswers(): MutableList<String?> {
        return answers
    }
}