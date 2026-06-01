let topics = [];
async function loadTopics() {
    const res = await fetch("/quiz/topics");
    topics = await res.json();

    //console.log(topics);
    renderTopics(topics);
}

// topics
const topicsSection = document.getElementById("topicsSection");
function renderTopics(topics) {
    topicsSection.innerHTML = `
        <h2>🎯 Topics</h2>
        <div class="topics-tree">
            
            ${topics.map(t => `
                <button class="topic-button" data-sub="${t.name}">${t.name}</button>
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
    function renderSubtopics(topicString) {
        const topic = topics.find(topic => topic.name === topicString);

        //console.log(topic.subtopics);
        subtopicsSection.innerHTML = `
            <h3>📌 Subtopics: ${topic.name}</h3>
            <div class="subtopic-item">
                ${topic.subtopics.map(st => `
                    <div class="subtopic-item-row">
                        <div class="subtopic-item-main">
                            <span>${st.name}</span>

                            <div>
                                <button class="diff-btn" data-st="${st.name}" data-diff="łatwy" style="background: #0f0">łatwy</button>
                                <button class="diff-btn" data-st="${st.name}" data-diff="średni" style="background: #ff0">średni</button>
                                <button class="diff-btn" data-st="${st.name}" data-diff="trudny" style="background: #f00">trudny</button>
                            </div>
                        </div>
                    </div>
                `).join("")}
            </div>
        `;

        document.querySelector(".subtopic-item")
            .addEventListener("click", (e) => {
                if (e.target.classList.contains("diff-btn")) {
                    const row = e.target.closest(".subtopic-item-row");
                    difficultyButtons(row, e.target.dataset.st, e.target.dataset.diff);
                }
            });
    }// END Subtopics
        // Details
        function difficultyButtons(row, st, diff) {
            const existingDetails = row.querySelector(".details-block");

            if (existingDetails) {
                existingDetails.remove();
            }

            const details = document.createElement("div");
            details.classList.add("details-block");

            const btn = document.createElement("button");
            btn.textContent = "Start Quiz";
            btn.classList.add("button-quiz");

            details.innerHTML = `
                <hr>
                <div class="stats">
                    <div class="stat">📘 Questions: 20</div>
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

            btn.onclick = () => {
                alert("Quiz start: " + st + " | " + diff);
            };
            
            details.appendChild(btn);
            row.appendChild(details);
        }// END Details

loadTopics().then(renderTopics);
