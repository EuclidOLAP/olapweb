MenuController.prototype.init = WW.commonSuperFunctions.init;

function MenuController() {

    this.htmlTplPath = "/panels/menu.html";
    this.$mdxTer;

    this.doInit = function() {

        this.$mdxTer = $('#' + this.divId + '_mdxTer');

//        this.$mdxTer.click(this, function(event) {
//            var ctl = event.data;
//            ctl.$mdxTer.css({
//                "background-color": "#39495e",
//                "color": "#ffffff"
//            });
//            if (ctl.$mdxTer.attr("data-exist") != "Y") {
//                var workAreaDivId = WW.findController("stage").createWorkArea();
//                WW.findFactory("work/mdxTerminal").newController().init(workAreaDivId);
//                ctl.$mdxTer.attr("data-exist", "Y");
//            }
//        });

        (function(_ctl_) {
             _ctl_.$mdxTer.css({
                 "background-color": "#39495e",
                 "color": "#ffffff"
             });
             if (_ctl_.$mdxTer.attr("data-exist") != "Y") {
                 var workAreaDivId = WW.findController("stage").createWorkArea();
                 WW.findFactory("work/mdxTerminal").newController().init(workAreaDivId);
                 _ctl_.$mdxTer.attr("data-exist", "Y");
             }
         })(this);
    };

    this.workAreaDivId = null;
};

WW.register(new MenuController(), "menu");