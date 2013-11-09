/**
 * Created with JetBrains WebStorm.
 * User: Jinay
 * Date: 11/9/13
 * Time: 1:50 AM
 * To change this template use File | Settings | File Templates.
 */
var APT_HUNT_LIB = APT_HUNT_LIB ? APT_HUNT_LIB : {};
var _ = _ ? _ : {};
var console = console ? console : {log: function () {}, dir: function () {} };

var APT_HUNT_LIB = (function () {

    var sh_sm_lib = function (valuesArg) {



    };

    sh_sm_lib.prototype = {
        constructor: sh_sm_lib,

        getSearchData: function(){
          var data = JSON.parse(localStorage.getItem("dummy"));
            return data;
        },

        createRow : function(values){
            $('#example').dataTable().fnAddData(values);
        },

        displaySearchResults: function(data){
            var that = this;
//            var data = this.getSearchData();
            $.each(data, function(i, v) {
            	$('#example').dataTable().fnAddData([i+1,v.addr+" "+v.city+" "+ v.zip, v.beds, "$"+v.rent, v.rating, v.numRoutes])
//                that.createRow([i+1,v.addr+" "+v.city+" "+ v.zip, v.beds, "$"+v.rent, v.rating, v.numRoutes])
            });
        },

        search : function(){
          var sub = $("#collapseOne");
          var home = $("#home").val();
          var office = $("#office").val();
          var sprice = $("#start_price input").val();
          var eprice = $("#end_price input").val();
          var rooms = $("#rooms").val();
          var bath = $("#bath").val();

          var req = {
              home: home,
              office: office,
              rentMin: sprice,
              rentMax: eprice,
              beds: rooms,
              save: 0
          };

            localStorage.setItem('SearchReq',JSON.stringify(req));

            $.ajax({
            	type: "POST",
            	url: "/app-service/search",
            	data: req,
            	success: this.displaySearchResults,
            	dataType: 'json'
            	});
            
//            this.displaySearchResults();
        }


    };  //End of prototypes

    return sh_sm_lib;

}());


