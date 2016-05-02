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
        "sAjaxSource": "/purchase/purchases",
        "bProcessing": true,
        "bFilter": false,
        "sPaginationType": "full_numbers",
        "bJQueryUI": true,
        "order": [[ 0, "desc" ]]
      });
    });
  </script>
</head>


<table id="companies" class="display">
  <thead>
  <tr>
    <th>Id</th>
    <th>Amount</th>
    <th>Paid</th>
    <th>Fuel</th>
    <th>Date</th>
    <th>Time</th>
    <th>Cashier</th>
    <th>Client</th>
  </tr>
  </thead>
  <tbody>
  </tbody>
</table>


</html>