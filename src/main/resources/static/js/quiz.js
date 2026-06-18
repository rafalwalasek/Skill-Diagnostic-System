let questions = [];
// console.log(window.location.search);
const urlParams = new URLSearchParams(window.location.search); // pobranie wartosci z adresu URL
const subtopicId = urlParams.get("subtopicId");
const difficulty = urlParams.get("difficulty");

fetch(`/quiz/questionsToDiagnostic?subtopicId=${subtopicId}&difficulty=${difficulty}`)
    .then(res => {
        // console.log("STATUS:", res.status);
        return res.json();
    })
    .then(data => {
        // console.log(data);
        questions = data;
        renderQuestion();
    });
// =============================================================================
// wyswietlenie tresci
let currentIndex = 0;
function renderQuestion() {
    const currentQuestion = questions[currentIndex];
    if (!currentQuestion) return;

    const quiz = document.getElementById("loadQuiz");
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
}// END wyswietlenie tresci
    // wyswietlenie odpowiedzi
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
    }// END wyswietlenie odpowiedzi
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
// ==================================================================================
// zapisanie odpowiedzi
function saveAnswer(id, answer) {
    answers[id] = answer;
}// END zapisanie odpowiedzi
    // wyslanie odpowiedzi i koniec skilla
    function submitQuiz() {
        // console.log(answers);
        // console.log(JSON.stringify(answers));
        fetch(`/quiz/userResults`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                userAnswerMap: answers
            })
        })
        .then(response => response.json())
        .then(result => {
            // console.log(score);
            const quiz = document.getElementById("loadQuiz");
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
        // powrot do strony głównej
        function goHome() {
            window.location.href = "index.html";
        }
        // END powrot do strony głównej