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
        var num = this.selectedIndex;
        var priceInput = $('#priceSelect');
        priceInput[0].selectedIndex = num;
        var payInput = $('#paid');
        var amountInput = $('#amountInput');
        payInput.val(priceInput.val() * amountInput.val());
    });
    $('#amountInput').on('keyup', function () {
        var payInput = $('#paid');
        var priceInput = $('#priceSelect');
        var amountInput = $('#amountInput');
        payInput.val(priceInput.val() * amountInput.val());
    });
});