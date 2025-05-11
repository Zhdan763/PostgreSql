<html>

<body>
<form action="import" enctype="multipart/form-data" method ="post">
<h1>Import</h1>

<table >
        <tr>
            <td>Action with the same id</td>
            <td><select class="select" name="table">
                                                <option>Ignore</option>
                                                <option>Update</option>
                                                <option>Error</option>

                                            </select></td>
        </tr>

    </table>
   <p><input type="file" name="f" ></p>
  <p> <input type="submit" value="Import" ></p>
  </form>
   <p>   <button  ><a href="journals">Back</a></button> </p>

</body>
</html>