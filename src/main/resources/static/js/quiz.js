let questions = [];
fetch("/quiz/questions?st=JAVA&diff=EASY")
    .then(res => {
        console.log("STATUS:", res.status);
        return res.json();
    })
    .then(data => {
        console.log("DATA:", data);
        questions = data;
        renderQuestion();
    });

let currentIndex = 0;
function renderQuestion() {
    const currentQuestion = questions[currentIndex];
    if (!currentQuestion) return;

    const quiz = document.getElementById("loadQuiz");
    quiz.innerHTML = `
        <div class="question-box">
            <h3>Question ${currentIndex + 1} / ${questions.length}</h3>
            <p>${currentQuestion.question}</p>

            <div class="answers">
                A
                B
                C
                D
            </div>
        </div>
    `;
}


// ${renderAnswer(currentQuestion.id, "A", currentQuestion.answerA)}
//                 ${renderAnswer(currentQuestion.id, "B", currentQuestion.answerB)}
//                 ${renderAnswer(currentQuestion.id, "C", currentQuestion.answerC)}
//                 ${renderAnswer(currentQuestion.id, "D", currentQuestion.answerD)}
// let questions = [];
// let currentIndex = 0;
// let answers = {};

// // LOAD QUESTIONS
// fetch('/quiz/questions')
//     .then(res => res.json())
//     .then(data => {
//         questions = data;
//         renderQuestion();
//     });

// function renderQuestion() {
//     const q = questions[currentIndex];
//     if (!q) return;

//     document.getElementById("quiz").innerHTML = `
//         <div class="question-box">
//             <h3>Question ${currentIndex + 1} / ${questions.length}</h3>
//             <p>${q.question}</p>

//             <div class="answers">
//                 ${renderAnswer(q.id, "A", q.answerA)}
//                 ${renderAnswer(q.id, "B", q.answerB)}
//                 ${renderAnswer(q.id, "C", q.answerC)}
//                 ${renderAnswer(q.id, "D", q.answerD)}
//             </div>
//         </div>
//     `;
// }

// function renderAnswer(id, key, text) {
//     const checked = answers[id] === key ? "checked" : "";

//     return `
//         <label>
//             <input type="radio"
//                    name="q${id}"
//                    value="${key}"
//                    ${checked}
//                    onchange="saveAnswer(${id}, '${key}')">
//             ${key}. ${text}
//         </label>
//     `;
// }

// function saveAnswer(id, answer) {
//     answers[id] = answer;
// }

// function nextQuestion() {
//     if (currentIndex < questions.length - 1) {
//         currentIndex++;
//         renderQuestion();
//     }
// }

// function prevQuestion() {
//     if (currentIndex > 0) {
//         currentIndex--;
//         renderQuestion();
//     }
// }

// function submitQuiz() {
//     const userAnswerList = Object.keys(answers).map(qId => ({
//         questionId: parseInt(qId),
//         answer: answers[qId]
//     }));

//     fetch('/quiz/submit', {
//         method: 'POST',
//         headers: {'Content-Type': 'application/json'},
//         body: JSON.stringify({ userAnswerList })
//     })
//     .then(res => res.json())
//     .then(result => {
//         document.getElementById("result").innerText =
//             "Twój wynik: " + result;
//     });
// }