MenuController.prototype.init = WW.commonSuperFunctions.init;

function MenuController() {

    this.htmlTplPath = "/panels/menu.html";
    this.$mdxTer;

    this.doInit = function() {
        this.$mdxTer = $('#' + this.divId + '_mdxTer');
        this.$mdxTer.click(this, function(event) {
            var ctl = event.data;
            ctl.$mdxTer.css({
                "background-color": "#39495e",
                "color": "#ffffff"
            });
            console.log(">>>>>>>>>>>>>>>>>>>>>>>>>>> click [MDX Terminal]");
            if (ctl.$mdxTer.attr("data-exist") != "Y") {
                var workAreaDivId = WW.findController("stage").createWorkArea();
                WW.findFactory("work/mdxTerminal").newController().init(workAreaDivId);
                ctl.$mdxTer.attr("data-exist", "Y");
            }
//            else {
//                this.workAreaDivId = WW.findController("stage").createWorkArea();
//                WW.findFactory("work/mdxTerminal").newController().init(this.workAreaDivId);
//            }
        });
    };

    this.workAreaDivId = null;
};

WW.register(new MenuController(), "menu");