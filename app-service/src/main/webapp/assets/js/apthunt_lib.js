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

    this.select = $("#reload_select");
    this.option = $("#reload_select option").remove();
    var that = this;
    this.load;

        $.each([1,2,3],function(i,v){
            var o = that.option.clone();
            o.val(i);
            o.text("Query "+i);
            that.select.append(o);
            //that.search([v.addr+" "+v.city+" "+ v.zip, v.beds, "$"+v.rent, v.rating, v.numRoutes, 0])
        });


     //   $("select").removeClass("chzn-done").css('display', 'inline').data('chosen', null);
     //   $("*[class*=chzn]").remove();
     //   this.select = $("#reload_select").clone();
     //   $("#reload_select").remove();
     //   $("#reload_select").appendTo("#reload div");
     //   $("#reload_select").chosen({width: "500px"});

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

        displaySearchResults: function(){
            var that = this;
            var data = this.getSearchData();
            $.each(data, function(i, v) {
                that.createRow([i+1,v.addr+" "+v.city+" "+ v.zip, v.beds, "$"+v.rent, v.rating, v.numRoutes])
            });
            $("#preloader").removeClass("loading");
            $("#input .glyphicon-minus-sign").trigger("click");
            $("#output .glyphicon-plus-sign").trigger("click");

        },

        unchosen: function() {
            return $(this).each(function() {
                var element = $(this);
                if(element.hasClass('chzn-done')){
                    //remove chosen
                    element.next('[id*=_chzn]').remove(); //Make sure its id contain _chzn
                    //remove chosen class in original combobox and make it visible
                    element.removeClass('chzn-done').css('display','block');
                }
            });
        },

        search : function(dhome,doffice,dsprice,deprice,drooms,dbath,flag){
          var sub = $("#collapseOne");
          var home = dhome || $("#home").val();
          var office = doffice || $("#office").val();
          var sprice = dsprice || $("#start_price input").val();
          var eprice = deprice || $("#end_price input").val();
          var rooms = drooms || $("#rooms").val();
          var bath = dbath || $("#bath").val();
          var save = flag;

          var req = {
              home: home,
              office: office,
              sprice: sprice,
              eprice: eprice,
              rooms: rooms,
              bath: bath,
              save: flag
          };

            localStorage.setItem('SearchReq',JSON.stringify(req));

            this.displaySearchResults();
        }


    };  //End of prototypes

    return sh_sm_lib;

}());


