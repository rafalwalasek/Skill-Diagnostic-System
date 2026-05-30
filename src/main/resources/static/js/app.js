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
    

    const section = document.getElementById("subtopicsSection");
    function renderSubtopics(topic) {
        section.innerHTML = `
            <h2>📌 Subtopics: ${topic.name}</h2>
            <div class="subtopics-row">
                ${topic.subtopics.map(sub => `
                    <div class="subtopic-row-item">
                        <span>${sub.name}</span>

                        <div>
                            <button class="diff-btn" data-sub="${sub.name}" data-diff="łatwy">łatwy</button>
                            <button class="diff-btn" data-sub="${sub.name}" data-diff="średni">średni</button>
                            <button class="diff-btn" data-sub="${sub.name}" data-diff="trudny">trudny</button>
                        </div>
                    </div>
                `).join("")}
            </div>
        `;

        bindDifficultyButtons(topic);
    }
        let selected = {};
        function bindDifficultyButtons(topicName) {
            document.querySelectorAll(".diff-btn").forEach(btn => {
                btn.addEventListener("click", () => {
                    selected = {
                        topic: topicName.name,
                        subtopic: btn.dataset.sub,
                        difficulty: btn.dataset.diff
                    };

                    renderDetails(selected);
                });
            });
        }
            const trainBtn = document.getElementById("trainBtn");
            const details = document.getElementById("detailsSection");
            function renderDetails(selected) {
                details.innerHTML = `
                    <div class="stat">🎯 Topic: ${selected.topic}</div>
                    <div class="stat">📌 Subtopic: ${selected.subtopic}</div>
                    <div class="stat">⚡ Difficulty: ${selected.difficulty}</div>
                `;

                trainBtn.disabled = false;

                trainBtn.addEventListener("click", () => {
                    console.log(selected);
                });
            }

loadTopics().then(renderTopics);

//------------------------------------------------------------------------------------

// let state = {
//     topics: [],
//     expanded: {},
//     selectedSubtopic: null,
//     stats: null,
//     progress: [],
//     recentResults: []
// }

// document.addEventListener("DOMContentLoaded", () => {
//     loadTopics();
// });

// async function loadTopics() {
//     const res = await fetch("/quiz/topics");
//     state.topics = await res.json();
//     renderTree();
// }

// function renderTree() {
//     const tree = document.getElementById("tree");
//     tree.innerHTML = "";

//     state.topics.forEach(topic => {

//         const topicDiv = document.createElement("div");
//         topicDiv.className = "topic";
//         topicDiv.innerText = topic.name;

//         topicDiv.onclick = () => {
//             state.expanded[topic.id] = !state.expanded[topic.id];
//             renderTree();
//         };

//         tree.appendChild(topicDiv);

//         if (state.expanded[topic.id]) {
//             topic.subtopics.forEach(sub => {

//                 const subDiv = document.createElement("div");
//                 subDiv.className = "subtopic";
//                 subDiv.innerText = sub.name;

//                 if (state.selectedSubtopic?.id === sub.id) {
//                     subDiv.classList.add("active");
//                 }

//                 subDiv.onclick = () => selectSubtopic(sub);

//                 tree.appendChild(subDiv);
//             });
//         }
//     });
// }

// async function selectSubtopic(subtopic) {
//     state.selectedSubtopic = subtopic;

//     const res = await fetch(`/quiz/subtopics/${subtopic.id}/stats`);
//     state.stats = await res.json();

//     renderTree();
//     renderDetails();
// }

// function renderDetails() {
//     const details = document.getElementById("details");
//     const btn = document.getElementById("trainBtn");

//     if (!state.stats) return;

//     details.innerHTML = `
//         <div class="stat">📘 Questions: ${state.stats.questionsCount}</div>
//         <div class="stat">📊 Mastery: ${state.stats.masteryPercent}%</div>
//         <div class="stat">🔁 Attempts: ${state.stats.attempts}</div>
//         <div class="stat">⭐ Last result: ${state.stats.lastResult}%</div>
//         <div class="stat">🏷 Status: ${state.stats.status}</div>
//     `;

//     btn.disabled = false;

//     btn.onclick = () => {
//         alert("Quiz start: subtopic " + state.selectedSubtopic.id);
//     };
// }
