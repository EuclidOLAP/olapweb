StageController.prototype.init = WW.commonSuperFunctions.init;

function StageController() {

    this.htmlTplPath = "/panels/stage.html";

    this.resize = function() {
        var div_left = "#" + this.divId + "_left" ;
        var div_star = "#" + this.divId + "_star" ;
        var div_menu = "#" + this.divId + "_menu" ;
        var div_right = "#" + this.divId + "_right" ;
        var div_fns = "#" + this.divId + "_fns" ;
        var div_work = "#" + this.divId + "_work" ;
        $(div_right).width($("#" + this.divId).width() - $(div_left).width());
        $(div_menu).height($(div_left).height() - $(div_star).height());
        $(div_work).height($(div_right).height() - $(div_fns).height());
    };

    this.doInit = function() {
        this.resize();
        WW.findController("menu").init(this.divId + "_menu");
    };

    this.createWorkArea = function() {
        var workAreaDivId = this.divId + "_work_" + WW.gn();
        $("#" + this.divId + "_work").append($("<div id='" + workAreaDivId + "'></div>"));
        return workAreaDivId;
    };
};

WW.register(new StageController(), "stage");