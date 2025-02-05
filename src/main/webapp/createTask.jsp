<html>

<link href="viewCreateUpdate.css" rel="stylesheet">
<body>
<form action="/createTask?journalId=${journalId}&journalName=${journalName}" method ="post">
<h2>Create Task</h2>
    <table >

        <tr>
            <td>Name</td>
            <td><input type="text" name="name"  /></td>
        </tr>
        <tr>
                    <td>Description</td>
                    <td><input type="text" name="descriptionTask"/></td>
                </tr>
      <tr>
            <td>Date</td>
            <td><input type="datetime-local" id="date" name="date"/></td>
      </tr>
         </table>

         <div>
         <button ><a href="/tasks?journalId=${journalId}&journalName=${journalName}">Back</a></button>
         <input type="submit" value="Create"/>
         </div>



	   </form>
</body>
<script src="/createTask.js"></script>
</html>
