/**
 * Created by AndreyZholudev on 4/1/2016.
 */



$(document).ready(function () {
    $('#clients_search').autocomplete({
        serviceUrl: '/purchase/clients',
        paramName: "tagName",
        delimiter: ",",
        transformResult: function(response) {

            return {
                //must convert json to javascript object before process
                suggestions: $.map($.parseJSON(response), function(item) {

                    return { value: item.tagName, data: item.id };
                })

            };

        }

    });
});