var APT_HUNT_LIB = APT_HUNT_LIB || {};
var _ = _ ? _ : {};
var console = console ? console : {};

$(document).ready(function () {

   <!-- $("#results .collapse").collapse(); -->

   $("#rooms").chosen({width: "100px"});
    //$("#reload_select").chosen({width: "500px"});


    $("#example").dataTable(
        {
            "sPaginationType": "full_numbers",
            "aoColumnDefs": [
                { "bSortable": false, "aTargets": [] }
             ]
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
        $("#preloader").addClass("loading");
        lib.search(flag=0);
    });

    $("#save").on("click",function(){
        $("#preloader").addClass("loading");
        lib.search(flag=1);
    });

    $("#load").on("click",function(){
        var index = $("#reload_select").val();
        var v = lib.load[index];
        lib.search([v.addr+" "+v.city+" "+ v.zip, v.beds, "$"+v.rent, v.rating, v.numRoutes, 0]);

    });

});
