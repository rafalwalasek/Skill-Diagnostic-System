document.addEventListener("DOMContentLoaded", () => {
    allQuestions();

    document.addEventListener("click", (e) => {
        const categoryBtn  = e.target.closest(".category-btn");
        if (!categoryBtn) return;

        categoryQuestions(categoryBtn.dataset.sub);
        attemptsCount(categoryBtn.dataset.sub);
    });
});
// all questions
function allQuestions() {
    fetch(`/quiz/allQuestionCount`)
    .then(response => response.json())
    .then(data => {
        const allQuestions = document.getElementById("allQuestionCount");
        allQuestions.textContent = data;
    })
    .catch(error => console.error("Błąd:", error));
}
// category question
function categoryQuestions(category) {
    const categoryName = document.getElementById("categoryName");
    categoryName.textContent = category;

    fetch(`/quiz/categoryQuestionCount?category=${category}`)
    .then(response => response.json())
    .then(data => {
        const categoryQuestion = document.getElementById("categoryQuestion");
        categoryQuestion.textContent = data;
    })
    .catch(error => console.error("Błąd:", error));
}
// zliczanie prob
function attemptsCount(category) {
    fetch(`/quiz/attemptsCount?category=${category}`)
    .then(response => response.json())
    .then(data => {
        const attempts = document.getElementById("attempts");
        attempts.textContent = data;
    })
    .catch(error => console.error("Błąd:", error));
}