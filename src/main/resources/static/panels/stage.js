function StageController() {

    var divId;

    this.init = function(div_id) {
        var _this = this;
        divId = div_id;

        // load html
        $.ajax({
            url: "/panels/stage.html",
            success: function(result) {
                $('#' + div_id).html(result.replace(/@PANEL@/g, div_id));
                _this.doInit();
            }
        });
    };

    this.resize = function() {
        var div_left = "#" + divId + "_left" ;
        var div_star = "#" + divId + "_star" ;
        var div_menu = "#" + divId + "_menu" ;
        var div_right = "#" + divId + "_right" ;
        var div_fns = "#" + divId + "_fns" ;
        var div_work = "#" + divId + "_work" ;
        $(div_right).width($("#" + divId).width() - $(div_left).width());
        $(div_menu).height($(div_left).height() - $(div_star).height());
        $(div_work).height($(div_right).height() - $(div_fns).height());
    };

    this.doInit = function() {
        this.resize();
        WW.findController("menu").init(divId + "_menu");
    };

    this.createWorkArea = function() {
        var workAreaDivId = divId + "_work" + '_' + WW.gn();
        $("#" + divId + "_work").append($("<div id='" + workAreaDivId + "'></div>"));
        return workAreaDivId;
    };
};

WW.register(new StageController(), "stage");