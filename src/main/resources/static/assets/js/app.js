document.addEventListener("DOMContentLoaded", () => {
    allQuestions();

    document.addEventListener("click", (e) => {
        const categoryBtn  = e.target.closest(".category-btn");
        if (!categoryBtn) return;

        categoryQuestions(categoryBtn.dataset.sub);
        attemptsCount(categoryBtn.dataset.sub);
    });

    document.addEventListener("click", (e) => {
        const menu = e.target.closest("a[data-page]");
        if (!menu) return;

        e.preventDefault();

        const page = menu.dataset.page;
        showPage(page);
    });

    showPage("dashboard");
});
//================================================================================
// all questions:
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
//=====================================================================================
// przelaczanie miedzy sekcjami (menu)
function showPage(page) {
    // 1. ukryj wszystkie sekcje
    document.querySelectorAll("section[data-page]").forEach(section => {
        section.style.display = "none";
    });
    // 2. pokaż wybraną
    const activeSection = document.querySelector(`section[data-page="${page}"]`);
    if (activeSection) {
        activeSection.style.display = "block";
    }
    // 3. active w menu
    document.querySelectorAll("a[data-page]").forEach(link => {
        link.parentElement.classList.remove("active");
    });

    const activeLink = document.querySelector(`a[data-page="${page}"]`);
    if (activeLink) {
        activeLink.parentElement.classList.add("active");
    }
}
//==================================================================================
