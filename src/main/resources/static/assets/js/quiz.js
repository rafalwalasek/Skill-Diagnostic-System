document.addEventListener("DOMContentLoaded", () => {
    const startBtn = document.getElementById("start-quiz-btn");

    startBtn.addEventListener("click", () => {
        const subtopicId = document.getElementById("subtopic-select").value;
        const difficulty = document.getElementById("difficulty-select").value;

        openQuiz(subtopicId, difficulty);
    });
});

let questions = [];
function openQuiz(subtopicId, difficulty) {
    fetch(`/quiz/questionsToDiagnostic?subtopicId=${subtopicId}&difficulty=${difficulty}`)
    .then(res => {
        return res.json();
    })
    .then(data => {
        questions = data;
        renderQuestion();
    });
}
//=============================================================================
// wyswietlenie tresci pytania i odpowiedzi
let currentIndex = 0;
function renderQuestion() {
    const currentQuestion = questions[currentIndex];
    if (!currentQuestion) return;

    const quiz = document.getElementById("quiz-panel");
    quiz.innerHTML = `
        <div class="question-box">
            <h3>Question ${currentIndex + 1} / ${questions.length}</h3>
            <p>${currentQuestion.content}</p>

            <div class="answers">
                ${renderAnswer(currentQuestion.id, "A", currentQuestion.answerA)}
                ${renderAnswer(currentQuestion.id, "B", currentQuestion.answerB)}
                ${renderAnswer(currentQuestion.id, "C", currentQuestion.answerC)}
                ${renderAnswer(currentQuestion.id, "D", currentQuestion.answerD)}
            </div>
            <div class="navigation">
                <button onclick="prevQuestion()">Previous</button>
                <button onclick="nextQuestion()">Next</button>
            </div>
            <button class="submit-btn" onclick="submitQuiz()">
                Finish Quiz
            </button>
        </div>
    `;
}// END wyswietlenie tresci pytania i odpowiedzi
    // odpowiedzi uzytkownika
    let answers = {};
    function renderAnswer(id, key, text) {
        const checked = answers[id] === key ? "checked" : "";

        return `
            <label>
                <input type="radio"
                    name="q${id}"
                    value="${key}"
                    ${checked}
                    onchange="saveAnswer(${id}, '${key}')">
                ${key}. ${text}
            </label>
        `;
    }// END odpowiedzi uzytkownika
    // przejscie pomiedzy pytaniami
    function nextQuestion() {
        if (currentIndex < questions.length - 1) {
            currentIndex++;
            renderQuestion();
        }
    }
    function prevQuestion() {
        if (currentIndex > 0) {
            currentIndex--;
            renderQuestion();
        }
    }// END przejscie pomiedzy pytaniami
// ==================================================
// zapisanie odpowiedzi
function saveAnswer(id, answer) {
    answers[id] = answer;
}// END zapisanie odpowiedzi
    // wyslanie odpowiedzi i koniec skilla
    function submitQuiz() {
        fetch(`/quiz/userResults`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                userAnswerMap: answers
            })
        })
        .then(response => response.json())
        .then(result => {
            const quiz = document.getElementById("quiz-panel");
            quiz.innerHTML = `
                <div class="result-box">
                    <h2>${result.subtopicName} ${result.difficulty}</h2>

                    <p>Score: ${result.score}/${result.totalQuestions}</p>
                    <p>${result.percentage}%</p>
                    <p>Date: ${result.date}</p>

                    <button onclick="goHome()">
                        Zakończ
                    </button>
                </div>
            `;
        })
    }// END wyslanie odpowiedzi i koniec skilla
