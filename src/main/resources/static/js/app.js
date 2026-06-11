let topicsAndSubtopics = [];
async function loadTopics() {
    const res = await fetch("/quiz/topics");
    topicsAndSubtopics = await res.json();

    //console.log(topicsAndSubtopics);
    renderTopics(topicsAndSubtopics);
}

// topics
const topicsSection = document.getElementById("topicsSection");
function renderTopics(topicsAndSubtopics) {
    topicsSection.innerHTML = `
        <h2>🎯 Topics</h2>
        <div class="topics-tree">
            
            ${topicsAndSubtopics.map(topic => `
                <button class="topic-button" data-sub="${topic.id}">${topic.topicTitle}</button>
            `).join("")}
        
        </div>
        <hr>
    `;

    document.querySelector(".topics-tree")
        .addEventListener("click", (e) => {
            if (e.target.classList.contains("topic-button")) {
                //console.log("klik:", e.target.textContent);
                renderSubtopics(e.target.dataset.sub);
            }
        });
}// END topics
    // Subtopics
    const subtopicsSection = document.getElementById("subtopicsSection");
    function renderSubtopics(topicId) {
        const id = Number(topicId);
        const topic = topicsAndSubtopics.find(topic => topic.id === id);

        //console.log(id);
        subtopicsSection.innerHTML = `
            <h3>📌 Subtopics: ${topic.topicTitle}</h3>
            <div class="subtopic-item">
                ${topic.subtopicsDTOS.map(st => `
                    <div class="subtopic-item-row">
                        <div class="subtopic-item-main">
                            <span>${st.subtopicTitle}</span>

                            <div>
                                <button class="diff-btn" data-subtopic-id="${st.id}" data-diff="EASY" style="background: #0f0">łatwy</button>
                                <button class="diff-btn" data-subtopic-id="${st.id}" data-diff="MEDIUM" style="background: #ff0">średni</button>
                                <button class="diff-btn" data-subtopic-id="${st.id}" data-diff="HARD" style="background: #f00">trudny</button>
                            </div>
                        </div>
                    </div>
                `).join("")}
            </div>
        `;
    }// END Subtopics
        // Details
        document.addEventListener("click", (e) => {
            console.log("TARGET:", e.target);
            console.log("ROW:", e.target.closest(".subtopic-item-row"));

            if (e.target.classList.contains("diff-btn")) {
                const row = e.target.closest(".subtopic-item-row");
                const subtopicId = e.target.dataset.subtopicId;
                const diff = e.target.dataset.diff;

                renderSubtopicOverview(row, subtopicId, diff);
            }
        });
            function renderSubtopicOverview(row, subtopicId, difficulty) {
                const existingDetails = row.querySelector(".details-block");
                const id = Number(subtopicId);

                // console.log(existingDetails);

                if (existingDetails) {
                    existingDetails.remove();
                }

                const details = document.createElement("div");
                details.classList.add("details-block");

                details.innerHTML = `
                    <hr>
                    <div class="stats">
                        <div class="stat">📘 Questions: <span class="count">0</span></div>
                        <div class="stat">🔁 Attempts: 0</div>
                        <div class="stat">🏷 Status: Not progress</div>
                    </div>
                    <div class="master-progress">
                        <div class="temat">
                            <span>📊 Mastery</span>
                            <span>10%</span>
                        </div>
                        <div class="progress">
                            <div class="progress-bar" style="width: 10%"></div>
                        </div>
                    </div>
                `;

                row.appendChild(details);

                getQuestionCount(row, subtopicId, difficulty);
            }// END Details

loadTopics();

// =================================================================================================
// zliczanie pytan z konkretnego poziomu
function getQuestionCount(row, subtopicId, difficulty) {
    fetch(`/quiz/questionCount?subtopicId=${subtopicId}&diff=${difficulty}`)
    .then(response => response.json())
    .then(data => {
        //console.log(data);
        const questionCount = row.querySelector(".count");
        questionCount.textContent = data;
    })
    .catch(error => console.error("Błąd:", error));
}



// function difficultyButtons(row, subtopicId, diff) {
//             const existingDetails = row.querySelector(".details-block");

//             if (existingDetails) {
//                 existingDetails.remove();
//             }

//             const details = document.createElement("div");
//             details.classList.add("details-block");

//             const btn = document.createElement("button");
//             btn.textContent = "Start Quiz";
//             btn.classList.add("button-quiz");

            // details.innerHTML = `
            //     <hr>
            //     <div class="stats">
            //         <div class="stat">📘 Questions: <span class="count">0</span></div>
            //         <div class="stat">🔁 Attempts: 0</div>
            //         <div class="stat">🏷 Status: Not progress</div>
            //     </div>
            //     <div class="master-progress">
            //         <div class="temat">
            //             <span>📊 Mastery</span>
            //             <span>10%</span>
            //         </div>
            //         <div class="progress">
            //             <div class="progress-bar" style="width: 10%"></div>
            //         </div>
            //     </div>
            // `;

            // btn.onclick = () => {
                // alert("Quiz start: " + st + " | " + diff);
                // window.open("quiz.html");
        //         window.open("quiz.html?st=JAVA&diff=EASY");
        //     };
            
        //     details.appendChild(btn);
        //     row.appendChild(details);

        //     getQuestionCount(row, st, diff);
        // }