document.addEventListener("DOMContentLoaded", () => {
    showPage("progress");

    document.addEventListener("click", (e) => {
        const link = e.target.closest("a[data-page]");
        if (!link) return;

        e.preventDefault();
        showPage(link.dataset.page);
        setActive(link);
    });
});
function showPage(pageId) {
    document.querySelectorAll("#views .page").forEach(p => p.classList.remove("active"));
    document.getElementById(pageId)?.classList.add("active");
}
function setActive(link) {
    document.querySelectorAll(".menu li").forEach(li => li.classList.remove("active"));
    link.parentElement.classList.add("active");
}