<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html>

    <head>
    </head>

    <link href="journals.css" rel="stylesheet">

    <body>

        <h2>Journals</h2>

        <div id="import"><button ><a href="import">Import</a></button></div>
<div></div>


        <div id="type" style="Display: none">${type}</div>
            <div id="filter" class="filter">
            <form class="form" action="journals" method="post">
                <div class="dropdown">
                    <button class="drop-btn">Filter by:</button>
                    <div class="dropdown-content">
                        <div>
                            <select class="select" name="table">
                                <option>name</option>
                                <option>description</option>
                                <option>date</option>
                            </select>
                            <input class="input" type="text" name="text">
                        </div>
                        <div>
                            <button class="submit" type="submit">Enter</button>
                        </div>

                    </div>
                </div>
            </form>
        </div>
        <div id="filter2" style="Display: none">
        <div id="filteredBy"><button >Filtered by ${type}: ${value} </button ></div>
        <div id="reset"><button ><a href="journals">Reset filter</a></button></div>
        </div>


        <div class="addbtn">
            <button class="add"><a href="createJournal.jsp">Add journal</a></button>
        </div>

        <form action="deleteJournalCheckbox"  method="post">


        <table>
            <tr>
                <th></th>
                <th id="name" onclick="f1();" ></th>
                <th id="description" onclick="f2();" ></th>
                <th id="date" onclick="f3();"></th>
                <th>Action</th>
            </tr>

            <c:forEach var="journal" items="${test}">
                <tr >
                    <td>
                    <input type="checkbox" name=id onChange="changeButtonState(this)" value=${journal.id}>
                    </td>
                    <td class="action" onclick="window.location='tasks?journalId=${journal.id}&journalName=${journal.name}';"
                    >${journal.name}</td>
                    <td class="action" onclick="window.location='tasks?journalId=${journal.id}&journalName=${journal.name}';"
                    >${journal.description}</td>
                    <td class="action" onclick="window.location='tasks?journalId=${journal.id}&journalName=${journal.name}';"
                    >${journal.date}</td>
                    <td>
                    <button><a href="updateJournal?id=${journal.id}&journalName=${journal.name}&journalDescription=${journal.description}">Update</a></button>
                    <button><a href="deleteJournal?id=${journal.id}">Delete</a></button>
                    </td>
                </tr>
            </c:forEach>
        </table>


     <input type="submit" id="invisible" name="action" value="Delete selected journal(s)" >
<input type="submit" id="invisible1" name="action" value="Export selected journal(s) with tasks" >
<input type="submit" id="invisible2" name="action" value="Export selected journal(s) without tasks" >
        </form>

    </body>
<script src="journals.js"></script>
    </html>