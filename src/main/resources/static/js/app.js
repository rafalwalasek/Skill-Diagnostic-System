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
            const details = document.createElement("div");

            details.innerHTML = `
                <hr>

                <h3>Temat: ${st}</h3>
                <h3>Trudność: ${diff}</h3>
            `;

            details.classList.add("details-block");
            if (row.contains(".details-block")) {
                row.appendChild(details);
            }
            
        }
        // END Details


    //     let selected = {};
    //     function bindDifficultyButtons(topicName) {
    //         document.querySelectorAll(".diff-btn").forEach(btn => {
    //             btn.addEventListener("click", () => {
    //                 selected = {
    //                     topic: topicName.name,
    //                     subtopic: btn.dataset.sub,
    //                     difficulty: btn.dataset.diff
    //                 };

    //                 renderDetails(selected);
    //             });
    //         });
    //     }
    //         const trainBtn = document.getElementById("trainBtn");
    //         const details = document.getElementById("detailsSection");
    //         function renderDetails(selected) {
    //             details.innerHTML = `
    //                 <div class="stat">🎯 Topic: ${selected.topic}</div>
    //                 <div class="stat">📌 Subtopic: ${selected.subtopic}</div>
    //                 <div class="stat">⚡ Difficulty: ${selected.difficulty}</div>
    //             `;

    //             trainBtn.disabled = false;

    //             trainBtn.addEventListener("click", () => {
    //                 console.log(selected);
    //             });
    //         }

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
