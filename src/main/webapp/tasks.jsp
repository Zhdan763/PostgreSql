<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>

</head>

<link href="tasks.css" rel="stylesheet">
<body>
<h2>Tasks by journal: ${journalName}</h2>

<div id="type" style="Display: none">${type}</div>

<div class="action1"><button class="add"><a href="journals">Back to journals</a></button></div>
<div class="action2" id="filter">

<form class="form" action="tasks?journalId=${journalId}&journalName=${journalName}" method="post">
<div class="dropdown">
<button class="btn">Filter by:</a></button>
<div class="dropdown-content">
                        <div class="content">
                            <select class="select" name="table">
                                <option>name</option>
                                <option>description</option>
                                <option>date</option>
                                <option>status</option>
                            </select>
                            <input class="input" type="text" name="text">
                        </div>
                        <div class="content">
                            <button class="submit" type="submit">Enter</button>
                        </div>

                    </div>
                    </div>
</form>
</div>
<div id="filter2" style="Display: none">
        <div id="filteredBy"><button >Filtered by ${type}: ${value} </button ></div>
        <div id="reset"><button >
        <a href="tasks?journalId=${journalId}&journalName=${journalName}">Reset filter</a></button></div>
        </div>
<div class="action3"><button class="add">
<a href="createTask?journalId=${journalId}&journalName=${journalName}">Add task</a></button></div>


<form action="deleteTaskCheckbox?journalId=${journalId}&journalName=${journalName}" method="post">
 <table>
        <tr>
            <th></th>
            <th id="name" onclick="f1('?journalId=${journalId}&journalName=${journalName}');"></th>
            <th id="description" onclick="f2('?journalId=${journalId}&journalName=${journalName}');"></th>
            <th id="date" onclick="f3('?journalId=${journalId}&journalName=${journalName}');"></th>
            <th id="status" onclick="f4('?journalId=${journalId}&journalName=${journalName}');"></th>
            <th>Action</th>
        </tr>




        <c:forEach var="task" items="${taskList}">

        <tr>
                <td>
                <input type="checkbox" name=id onChange="changeButtonState(this)" value=${task.id}>
                </td>
                <td>${task.name}</td>
                <td>${task.description}</td>
                <td>${task.date}</td>
                <td>${task.taskStatus}</td>

                <td>
                 <button><a href="updateTask?taskId=${task.id}&journalId=${journalId}&journalName=${journalName}&taskName=${task.name}&taskDescription=${task.description}">Update</a></button>
                 <button><a href="deleteTask?taskId=${task.id}&journalId=${journalId}&journalName=${journalName}">Delete</a></button>
                </td>
        </tr>


        </c:forEach>

    </table>
    <input type="submit" id="invisible" name="action" value="Delete selected task(s)">
    <input type="submit" id="invisible1" name="action" value="Export selected task(s) with journal" >
    <input type="submit" id="invisible2" name="action" value="Export selected task(s) without journal" >
</form>

</body>
<script src="tasks.js"></script>
</html>
