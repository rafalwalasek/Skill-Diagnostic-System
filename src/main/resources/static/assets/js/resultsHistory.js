document.addEventListener("DOMContentLoaded", () => {
    fetch(`/quiz/resultsHistory`)
    .then(response => response.json())
    .then(data => {
        console.log(data);

        const resultsBody = document.getElementById("resultsBody");
        
        resultsBody.innerHTML = "";

        data.forEach(result => {
            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${result.id}</td>
                <td>${result.score}/${result.totalQuestions}</td>
                <td>${result.percentage}%</td>
                <td>${result.date}</td>
                <td>${result.subtopicName}</td>
                <td>${result.difficulty}</td>
                
                
            `;

            resultsBody.appendChild(row);
        });
    })
    .catch(error => console.error("Błąd:", error));
});