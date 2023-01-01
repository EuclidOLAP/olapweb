StageController.prototype.init = WW.commonSuperFunctions.init;

function StageController() {

    this.htmlTplPath = "/panels/stage.html";

    this.$dialog;
    this.$dialogClose;
    this.$dialogContent;

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
        this.$dialogClose = $("#" + this.divId + "_dialogClose");
        this.$dialog = $("#" + this.divId + "_dialog");
        this.$dialogContent = $("#" + this.divId + "_dialogContent");

        this.resize();
        WW.findController("menu").init(this.divId + "_menu");

        this.$dialogClose.click(this, function(event) {
            var ctl = event.data;
            ctl.$dialog.hide();
        });
    };

    this.createWorkArea = function() {
        var workAreaDivId = this.divId + "_work_" + WW.gn();
        $("#" + this.divId + "_work").append($("<div id='" + workAreaDivId + "'></div>"));
        return workAreaDivId;
    };

    this.popDialog = function() {
        this.$dialogContent.children().remove();
        var chiDivId = this.divId + "_dialogContent_" + WW.gn();
        this.$dialogContent.append("<div id='" + chiDivId + "'></div>");
        this.$dialog.css({
            left: "100px",
            top: "10px",
            width: "800px",
            height: "420px",
            "background-color": "#EEEEEE"
        });
        this.$dialog.show();

        $("#" + chiDivId).height(this.$dialog.height() - this.$dialogClose.height());

        return chiDivId;
    };
};

WW.register(new StageController(), "stage");