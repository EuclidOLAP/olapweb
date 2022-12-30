function MenuController() {

    var divId;

    this.init = function(div_id) {
        var _this = this;
        divId = div_id;

        // load html
        $.ajax({
            url: "/panels/menu.html",
            success: function(result) {
                $('#' + div_id).html(result.replace(/@PANEL@/g, div_id));
                _this.doInit();
            }
        });
    };

    this.doInit = function() {
        $('#' + divId + '_mdxTer').click(function() {
            console.log(">>>>>>>>>>>>>>>>>>>>>>>>>>> click [MDX Terminal]");
            if (this.workAreaDivId) {
            } else {
                this.workAreaDivId = WW.findController("stage").createWorkArea();
                WW.findFactory("work/mdxTerminal").newController().init(this.workAreaDivId);
            }
        });
    };

    this.workAreaDivId = null;
};

WW.register(new MenuController(), "menu");