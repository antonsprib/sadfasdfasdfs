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
    const tr = document.createElement("tr");
    tr.innerHTML = `
        <td>${task.id}</td>
        <td>${task.title}</td>
        <td>${task.description}</td>
        <td>${task.assignedUserId}</td>
    `;
    document.getElementById("table-body").appendChild(tr);
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