let state = {
    topics: [],
    expanded: {},
    selectedSubtopic: null,
    stats: null,
    progress: [],
    recentResults: []
}

document.addEventListener("DOMContentLoaded", () => {
    loadTopics();
});

async function loadTopics() {
    const res = await fetch("/quiz/topics");
    state.topics = await res.json();
    renderTree();
}

function renderTree() {
    const tree = document.getElementById("tree");
    tree.innerHTML = "";

    state.topics.forEach(topic => {

        const topicDiv = document.createElement("div");
        topicDiv.className = "topic";
        topicDiv.innerText = topic.name;

        topicDiv.onclick = () => {
            state.expanded[topic.id] = !state.expanded[topic.id];
            renderTree();
        };

        tree.appendChild(topicDiv);

        if (state.expanded[topic.id]) {
            topic.subtopics.forEach(sub => {

                const subDiv = document.createElement("div");
                subDiv.className = "subtopic";
                subDiv.innerText = sub.name;

                if (state.selectedSubtopic?.id === sub.id) {
                    subDiv.classList.add("active");
                }

                subDiv.onclick = () => selectSubtopic(sub);

                tree.appendChild(subDiv);
            });
        }
    });
}

async function selectSubtopic(subtopic) {
    state.selectedSubtopic = subtopic;

    const res = await fetch(`/quiz/subtopics/${subtopic.id}/stats`);
    state.stats = await res.json();

    renderTree();
    renderDetails();
}

function renderDetails() {
    const details = document.getElementById("details");
    const btn = document.getElementById("trainBtn");

    if (!state.stats) return;

    details.innerHTML = `
        <div class="stat">📘 Questions: ${state.stats.questionsCount}</div>
        <div class="stat">📊 Mastery: ${state.stats.masteryPercent}%</div>
        <div class="stat">🔁 Attempts: ${state.stats.attempts}</div>
        <div class="stat">⭐ Last result: ${state.stats.lastResult}%</div>
        <div class="stat">🏷 Status: ${state.stats.status}</div>
    `;

    btn.disabled = false;

    btn.onclick = () => {
        alert("Quiz start: subtopic " + state.selectedSubtopic.id);
    };
}
