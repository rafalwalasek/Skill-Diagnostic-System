document.addEventListener("DOMContentLoaded", () => {
    loadTopics();

    document.addEventListener("change", (e) => {
        switch (e.target.id) {
            case "topic-select": {
                const subtopicBox = document.getElementById("subtopic-box");
                const subtopicSelectEl = document.getElementById("subtopic-select");

                const topicId = Number(e.target.value);

                if (!topicId) {
                    subtopicSelectEl.innerHTML = '<option value="">Select subtopic</option>';
                    subtopicBox.style.display = "none";

                    document.getElementById("difficulty-box").style.display = "none";
                    document.getElementById("difficulty-select").value = "";

                    buttonState();
                    return;
                }

                const topic = topics.find(t => t.id === topicId);
                if (!topic) return;

                subtopicSelect(topic.subtopicsDTOS);
                buttonState();
                break;
            }
            case "subtopic-select": {
                const difficultyBox = document.getElementById("difficulty-box");
                const difficultySelect = document.getElementById("difficulty-select");

                if (!e.target.value) {
                    difficultyBox.classList.remove("visible");
                    difficultyBox.style.display = "none";

                    difficultySelect.value = "";
                    buttonState();
                    return;
                }

                difficultyBox.style.display = "block";

                setTimeout(() => {
                    difficultyBox.classList.add("visible");
                }, 10);

                buttonState();
                break;
            }
            case "difficulty-select": {
                buttonState();
                break;
            }
        }
    });
});
//==================================================================================
// wczytanie tematów i podtematów z bazy
let topics = [];
async function loadTopics() {
    const response = await fetch(`/quiz/topics`);
    topics = await response.json();

    topicSelect(topics);
}
    function topicSelect(topics) {
        const topicSelect = document.getElementById("topic-select");

        topicSelect.innerHTML = '<option value="">Select category</option>';

        topics.forEach(topic => {
            const option = document.createElement("option");
            option.value = topic.id;
            option.textContent = topic.topicTitle;

            topicSelect.appendChild(option);
        });
    }
    function subtopicSelect(subtopics) {
        const subtopicSelect = document.getElementById("subtopic-select");
        const subtopicBox = document.getElementById("subtopic-box");

        subtopicSelect.innerHTML = '<option value="">Select subtopic</option>';

        subtopics.forEach(subtopic => {
            const option = document.createElement("option");
            option.value = subtopic.id;
            option.textContent = subtopic.subtopicTitle;

            subtopicSelect.appendChild(option);
        });

        subtopicBox.style.display = "block";
        subtopicBox.classList.remove("visible");

        setTimeout(() => {
            subtopicBox.classList.add("visible");
        }, 10);
    }
    // aktywacja i dezaktywacja przycisku
    function buttonState() {
        const topic = document.getElementById("topic-select").value;
        const subtopic = document.getElementById("subtopic-select").value;
        const difficulty = document.getElementById("difficulty-select").value;
        const btn = document.getElementById("start-quiz-btn");
        
        if (topic && subtopic && difficulty) {
            btn.disabled = false;
        } else {
            btn.disabled = true;
        }
    }