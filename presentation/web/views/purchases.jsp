<!DOCTYPE html>
<html>
<head>
    <link href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css"
          rel="stylesheet" type="text/css" media="all" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js" type="text/javascript"></script>
    <script src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"
            type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#companies").dataTable({
                "bServerSide": true,
                "sAjaxSource": "/lot/lots",
                "bProcessing": true,
                "bFilter": false,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true
            });
        });
    </script>
</head>


        <table id="companies" class="display">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Start time</th>
                <th>End time</th>
                <th>Current bid</th>
                <th>Total bids</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>


</html>