function MdxTerminalFactory() {

    MdxTerminalController.prototype.init = WW.commonSuperFunctions.init;

    function MdxTerminalController() {

        this.htmlTplPath = "/panels/work/mdxTerminal.html";

        this.$txtArea;
        this.$result;

        this.lastMultiDimsResult;

        this.doInit = function() {
            console.log("<<<<<<<<<<<<<<<<<<<<<<<< MdxTerminalController.doInit()");

            this.$txtArea = $('#' + this.divId + '_mdxTxtArea');
            this.$result = $('#' + this.divId + '_result');

            $("#" + this.divId + "_smoothLine").click(this, function(event) {
                var chartCtl = WW.findFactory("chart/chart").newController();
                chartCtl.init(WW.findController("stage").popDialog(), function() {
                    chartCtl.paint("smooth_line", event.data.lastMultiDimsResult);
                });
            });

            $("#" + this.divId + "_chartBar").click(this, function(event) {
                var stageCtl = WW.findController("stage");
                var tmpPanelId = stageCtl.popDialog();
                console.log("####################### tmpPanelId = " + tmpPanelId);
                // alert("// todo _chartBar");
                var chartCtl = WW.findFactory("chart/chart").newController();
                chartCtl.init(tmpPanelId, function() {
                    chartCtl.paint("basic_bar", event.data.lastMultiDimsResult);
                });
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
                        controller.lastMultiDimsResult = JSON.parse(data.result);
                        console.log(controller.lastMultiDimsResult);

//                        controller.$result.val(data.result);
                        controller.$result.val(JSON.stringify(controller.lastMultiDimsResult, null, "  "));
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