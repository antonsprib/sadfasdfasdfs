fetch("http://localhost:8080/tasks", {
    method: "get"
}).then(
    resp => resp.json()
).then(tasks => {
    console.log(tasks);

    for (const task of tasks) {
        addTask(task);
    }
});

function addTask(task) {
    const div = document.createElement("div");
    div.innerHTML =
        "<span>TITLE: " + task.title + "</span><br>" +
        "<span>DESCRIPTION: " + task.description + "</span><br>" +
        "<span>ID: " + task.id + " </span><br>" +
        "<span>User: " + task.assignedUserId + " </span><br><br>";
    document.getElementsByTagName("body")[0].appendChild(div);
}