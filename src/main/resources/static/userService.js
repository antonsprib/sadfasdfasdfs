function loadUsers() {
    fetch("/users", {
        method: "get",
        headers: {
            'Authorization': 'Basic ' + btoa("FBI:adminPass")
        }
    }).then(
        resp => resp.json()
    ).then(users => {
        for (const user of users) {
            addUsers(user);
        }
    });
}

function addUsers(user) {
    const tr = document.createElement("tr");
    tr.innerHTML = `
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.lastName}</td>
        <td>${user.age}</td>
    `;
    document.getElementById("table-body").appendChild(tr);
}

function createUser() {
    const name = document.getElementById("name").value;
    const lastName = document.getElementById("lastname").value;
    const age = document.getElementById("age").value;
    const email = document.getElementById("username").value;

    fetch("/users", {
        method: "post",
        body: JSON.stringify({
            name: name,
            lastName: lastName,
            age: age,
            email: email
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Authorization': 'Basic ' + btoa("FBI:adminPass")
        }
    }).then(() => {
        window.location.href = "/users.html";
    });
}