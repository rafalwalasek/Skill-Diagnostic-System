let topicsAndSubtopics = [];
async function loadTopics() {
    const res = await fetch("/quiz/topics");
    topicsAndSubtopics = await res.json();

    //console.log(topicsAndSubtopics);
    renderTopics(topicsAndSubtopics);
}

// nasluchiwanie co zostalo klikniete
document.addEventListener("click", (e) => {
    // console.log("CLICK WORKS");
    // console.log("CLICK:", e.target);

    const topicBtn = e.target.closest(".topic-button");
    const diffBtn = e.target.closest(".diff-btn");

    if (!topicBtn && !diffBtn) return;

    if (topicBtn) {
        renderSubtopics(topicBtn.dataset.sub);
        return;
    }
    if (diffBtn) {
        const container = diffBtn.closest(".subtopic-container");
        if (!container) return;

        const details = container.querySelector(".subtopic-details");

        const subtopicId = diffBtn.dataset.subtopicId;
        const diff = diffBtn.dataset.diff;

        //console.log("DIFF CLICK:", { subtopicId, diff, row });
        renderSubtopicOverview(details, subtopicId, diff);
        return;
    }
});// END nasluchiwanie co zostalo klikniete

// topics
const topicIcons = {
    "Java": "☕"
};
const topicsSection = document.getElementById("topicsSection");
function renderTopics(topicsAndSubtopics) {
    topicsSection.innerHTML = `
        <h2>🎯 Topics</h2>
        <div class="topics-tree">
            
            ${topicsAndSubtopics.map(topic => `
                <button class="topic-button" data-sub="${topic.id}">
                    <span class="topic-icon">
                        ${topicIcons[topic.topicTitle] ?? "📘"}
                    </span>
                    ${topic.topicTitle}
                </button>
            `).join("")}
        
        </div>
    `;
}// END topics
    // Subtopics
    const subtopicsSection = document.getElementById("subtopicsSection");
    function renderSubtopics(topicId) {
        const id = Number(topicId);
        const topic = topicsAndSubtopics.find(topic => topic.id === id);

        //console.log(id);
        subtopicsSection.innerHTML = `
            <div class="subtopics-box">
                <h3>📌 Subtopics: ${topic.topicTitle}</h3>
                <div class="subtopic-list">

                    ${topic.subtopicsDTOS.map(st => `

                        <div class="subtopic-container">

                            <div class="subtopic-item-row">
                                <div class="subtopic-title">
                                    ${st.subtopicTitle}
                                </div>
                                <div class="difficulty-buttons">
                                    <button 
                                        class="diff-btn easy" 
                                        data-subtopic-id="${st.id}" 
                                        data-diff="EASY">
                                        łatwy
                                    </button>
                                    <button 
                                        class="diff-btn medium" 
                                        data-subtopic-id="${st.id}" 
                                        data-diff="MEDIUM">
                                        średni
                                    </button>
                                    <button 
                                        class="diff-btn hard" 
                                        data-subtopic-id="${st.id}" 
                                        data-diff="HARD">
                                        trudny
                                    </button>        
                                </div>
                            </div>
                            <div class="subtopic-details"></div>

                        </div>

                    `).join("")}

                </div>
            </div>
        `;
    }// END Subtopics
        // Details
        function renderSubtopicOverview(details, subtopicId, difficulty) {
            const existingDetails = details.querySelector(".details-block");

            // console.log(existingDetails);

            if (existingDetails) {
                existingDetails.remove();
                details.classList.remove("active");
                return;
            }

            const detailsBlock = document.createElement("div");
            detailsBlock.classList.add("details-block");

            detailsBlock.innerHTML = `
                <div class="stats">
                    <div class="stat">
                        📘 Questions: 
                        <span class="question-count">0</span>
                    </div>
                    <div class="stat">
                        🔁 Attempts: 
                        <span class="attempt-count">0</span>
                    </div>
                    <div class="stat">
                        🏷 Status: 
                        <span class="status">Not started</span>
                    </div>
                </div>
                <div class="master-progress">
                    <div class="master-header">
                        <span>📊 Mastery</span>
                        <span class="master-value">0%</span>
                    </div>
                    <div class="progress">
                        <div class="progress-bar"></div>
                    </div>
                </div>
                <button 
                    class="button-quiz"
                    data-id="${subtopicId}"
                    data-diff="${difficulty}">
                    Start test
                </button>
            `;

            details.appendChild(detailsBlock);
            details.classList.add("active");

            getQuestionCount(detailsBlock, subtopicId, difficulty);
            getAttemptCount(detailsBlock, subtopicId, difficulty);
            getSkillProgress(detailsBlock, subtopicId, difficulty);

            const startQuiz = detailsBlock.querySelector(".button-quiz");
            startQuiz.addEventListener("click", () => {
                startSkillDiagnostic(subtopicId, difficulty);
            });
        }// END Details

loadTopics();
// =================================================================================================
// zliczanie pytan z konkretnego poziomu
function getQuestionCount(row, subtopicId, difficulty) {
    fetch(`/quiz/questionCount?subtopicId=${subtopicId}&diff=${difficulty}`)
    .then(response => response.json())
    .then(data => {
        //console.log(data);
        const questionCount = row.querySelector(".question-count");
        questionCount.textContent = data;
    })
    .catch(error => console.error("Błąd:", error));
}// END zliczanie pytan z konkretnego poziomu
// zliczanie prob
function getAttemptCount(detailsBlock, subtopicId, difficulty) {
    fetch(`/quiz/attempts?subtopicId=${subtopicId}&difficulty=${difficulty}`)
    .then(response => response.json())
    .then(data => {
        //console.log(data);
        const attemptCount = detailsBlock.querySelector(".attempt-count");
        attemptCount.textContent = data;
    })
    .catch(error => console.error("Błąd:", error));
}// END zliczanie prob
// progres paska i postepu
function getSkillProgress(detailsBlock, subtopicId, difficulty) {
    fetch(`/quiz/progress?subtopicId=${subtopicId}&difficulty=${difficulty}`)
        .then(response => response.json())
        .then(data => {
            const value = detailsBlock.querySelector(".master-value");
            const bar = detailsBlock.querySelector(".progress-bar");
            const status = detailsBlock.querySelector(".status");

            value.textContent = `${data.mastery}%`;
            bar.style.width = `${data.mastery}%`;

            const statusLabels = {
                NOT_STARTED: "Not started",
                IN_PROGRESS: "In progress",
                COMPLETED: "Completed"
            };
            status.textContent = statusLabels[data.status];
            status.className = `status ${data.status.toLowerCase()}`;
        })
        .catch(error => console.error(error));
}// END progres paska i postepu
    
// przejscie do diagnostyki
function startSkillDiagnostic(subtopicId, difficulty) {
    window.location.href = `quiz.html?subtopicId=${subtopicId}&difficulty=${difficulty}`;
}