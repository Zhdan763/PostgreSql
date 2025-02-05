<html>
<link href="viewCreateUpdate.css" rel="stylesheet">
<body>
<form action="/updateJournal?journalId=${journalId}" method ="post">
<h2>Update Journal</h2>
    <table >
 <tr>
            <td>Name</td>
            <td><input type="text" value=${journalName} name="name"/></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><input type="text" value=${journalDescription} name="description"/></td>
        </tr>

    </table>
    <div>
    <button ><a href="/journals">Back</a></button>
     <input type="submit" value="Update"/>
    </div>
	   </form>
</body>
</html>

