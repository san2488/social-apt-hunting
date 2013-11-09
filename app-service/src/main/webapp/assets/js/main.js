var APT_HUNT_LIB = APT_HUNT_LIB || {};
var _ = _ ? _ : {};
var console = console ? console : {};

$(document).ready(function () {

   <!-- $("#results .collapse").collapse(); -->

   $("#rooms").chosen({width: "100px"});
    //$("#bath").chosen({width: "100px"});


    $("#example").dataTable(
        {
            "sPaginationType": "full_numbers",
            "aoColumnDefs": [ {
                "sType": "formatted-num",
                "aTargets": [1,2,3,4,5]
            } ]
        });


    $(".glyphicon-minus-sign").on("click",function(){
        $(this).parents(".panel-heading").siblings().collapse("hide");
        $(this).parents(".expand_collapse_ui").children().each(function(){
            $(this).toggle();
        });
    });

    $(".glyphicon-plus-sign").on("click",function(){
        $(this).parents(".panel-heading").siblings().collapse("show");
        $(this).parents(".expand_collapse_ui").children().each(function(){
            $(this).toggle();
        });
    });

    var lib = new APT_HUNT_LIB({});

    $(".search button").on("click",function(){
        $("#input .glyphicon-minus-sign").trigger("click");
        $("#output .glyphicon-plus-sign").trigger("click");
        lib.search();
    });


});
