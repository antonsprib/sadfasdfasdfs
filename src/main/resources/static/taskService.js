function loadTasks() {
    fetch("/tasks", {
        method: "get"
    }).then(
        resp => resp.json()
    ).then(tasks => {
        for (const task of tasks) {
            addTask(task);
        }
    });
}

function addTask(task) {
    const div = document.createElement("div");
    div.innerHTML =
        "<span>TITLE: " + task.title + "</span><br>" +
        "<span>DESCRIPTION: " + task.description + "</span><br>" +
        "<span>ID: " + task.id + " </span><br>" +
        "<span>User: " + task.assignedUserId + " </span><br><br>";
    document.getElementsByTagName("body")[0].appendChild(div);
}

function createTask() {
    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;

    fetch("/tasks", {
        method: "post",
        body: JSON.stringify({
            title: title,
            description: description
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(() => {
        window.location.href = "/index.html";
    });
}