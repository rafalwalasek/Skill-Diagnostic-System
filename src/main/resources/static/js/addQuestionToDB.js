document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("questionForm");

    form.addEventListener("submit", (e) => {
        e.preventDefault();

        const questionData = {
            content: document.getElementById("content").value,
            answerA: document.getElementById("answerA").value,
            answerB: document.getElementById("answerB").value,
            answerC: document.getElementById("answerC").value,
            answerD: document.getElementById("answerD").value,
            correctAnswer: document.getElementById("correctAnswer").value,
            difficulty: document.getElementById("difficulty").value,
            category: document.getElementById("category").value
        }

        fetch("/quiz/questions", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(questionData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error " + response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log("Saved question:", data);
        })
        .catch(error => {
            console.error("Error:", error);
        });
    });
});