$(document).ready(function () {
    $('#clients_search').autocomplete({
        serviceUrl: '/purchase/clients',
        paramName: "tagName",
        delimiter: ",",
        transformResult: function (response) {
            return {
                //must convert json to javascript object before process
                suggestions: $.map($.parseJSON(response), function (item) {
                    return {value: item.tagName, data: item.id};
                })
            };
        }
    });
    $('#fuelInput').on('change', function () {
        var num = this.val();
        var priceInput = $('#priceSelect');
        priceInput.val(num);
        var payInput = $('#paid');
        var amountInput = $('#amount');
        payInput.val(priceInput.val() * amountInput.val());
    });
});