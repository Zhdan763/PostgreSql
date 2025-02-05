<html>
<link href="viewCreateUpdate.css" rel="stylesheet">
<body>
<form action="/updateTask?taskId=${taskId}&journalId=${journalId}&journalName=${journalName}" method ="post">
<h2>Update Task</h2>
    <table >

        <tr>
            <td>Name</td>
            <td><input type="text" value=${taskName} name="name"/></td>
        </tr>
        <tr>
                    <td>Description</td>
                    <td><input type="text" value=${taskDescription} name="descriptionTask"/></td>
                </tr>
      <tr>
            <td>Date</td>
            <td><input type="datetime-local" id="date" name="date"/></td>
      </tr>
    </table>
    <div>
             <button ><a href="/tasks?journalId=${journalId}&journalName=${journalName}">Back</a></button>
              <input type="submit" value="Update"/>
             </div>
	   </form>
</body>
<script src="/updateTask.js"></script>
</html>
