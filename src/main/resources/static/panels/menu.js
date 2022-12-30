MenuController.prototype.init = WW.commonSuperFunctions.init;

function MenuController() {

    this.htmlTplPath = "/panels/menu.html";

    this.doInit = function() {
        $('#' + this.divId + '_mdxTer').click(function() {
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