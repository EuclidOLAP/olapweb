function MdxTerminalFactory() {

    MdxTerminalController.prototype.init = WW.commonSuperFunctions.init;

    function MdxTerminalController() {

        this.htmlTplPath = "/panels/work/mdxTerminal.html";

        this.$txtArea;
        this.$result;

        this.doInit = function() {
            console.log("<<<<<<<<<<<<<<<<<<<<<<<< MdxTerminalController.doInit()");

            this.$txtArea = $('#' + this.divId + '_mdxTxtArea');
            this.$result = $('#' + this.divId + '_result');

            $("#" + this.divId + "_chartBar").click(this, function(event) {
                var stageCtl = WW.findController("stage");
                var tmpPanelId = stageCtl.popDialog();
                console.log("####################### tmpPanelId = " + tmpPanelId);
                // alert("// todo _chartBar");
                WW.findFactory("chart/chart").newController().init(tmpPanelId);
            });

            $('#' + this.divId + '_execBtn').click(this, function(event) {
                var controller = event.data;
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: "/api/execMdx",
                    contentType: "application/json; charset=UTF-8",
                    data: JSON.stringify({
                        mdx: controller.$txtArea.val()
                    }),
                    success: function (data) {
//                        console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//                        console.log(result);
//                        console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>>>>>>>>>>>>>>");
                        controller.$result.val(data.result);
                    }
                });
            });
        };
    }

    this.newController = function() {
        return new MdxTerminalController();
    };
};

WW.registerFactory(new MdxTerminalFactory(), "work/mdxTerminal");