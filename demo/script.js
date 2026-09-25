let score = 0;
let currentQuestion = 0;

let questions = [
    {
        question: "What is the capital of India?",
        options: ["Mumbai", "New Delhi", "Pune", "Kolkata"],
        answer: "New Delhi"
    },
    {
        question: "Which language are we using for the backend?",
        options: ["Python", "Java", "C", "PHP"],
        answer: "Java"
    },
    {
        question: "Which database are we planning to use?",
        options: ["MySQL", "MongoDB", "PostgreSQL", "Oracle"],
        answer: "PostgreSQL"
    }
];

function startQuiz() {
    document.getElementById("quiz").style.display = "block";
    showQuestion();
}

function showQuestion() {
    let q = questions[currentQuestion];

    document.getElementById("question").innerText =
        "Question " + (currentQuestion + 1);

    document.getElementById("questionText").innerText = q.question;

    let buttons = document.querySelectorAll("#quiz button");

    buttons[0].innerText = q.options[0];
    buttons[1].innerText = q.options[1];
    buttons[2].innerText = q.options[2];
    buttons[3].innerText = q.options[3];

    document.getElementById("result").innerText = "";
}

function checkAnswer(answer) {
    if (answer === questions[currentQuestion].answer) {
        score = score + 10;
        document.getElementById("result").innerText = "Correct! +10 points";
    } else {
        document.getElementById("result").innerText = "Wrong answer!";
    }
}

function nextQuestion() {
    currentQuestion++;

    if (currentQuestion < questions.length) {
        showQuestion();
    } else {
        document.getElementById("question").innerText = "Quiz Complete!";
        document.getElementById("questionText").innerText =
            "Your score: " + score;
        document.getElementById("result").innerText = "";
    }
}