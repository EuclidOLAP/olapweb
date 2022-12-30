function MdxTerminalFactory() {

    MdxTerminalController.prototype.init = WW.commonSuperFunctions.init;

    function MdxTerminalController() {

        this.htmlTplPath = "/panels/work/mdxTerminal.html";

        this.$txtArea;

        this.doInit = function() {
            console.log("<<<<<<<<<<<<<<<<<<<<<<<< MdxTerminalController.doInit()");

            this.$txtArea = $('#' + this.divId + '_mdxTxtArea')

            $('#' + this.divId + '_execBtn').click(this.$txtArea, function(event) {
                var txtArea = event.data;
                // console.log(txtArea.val());
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: "/api/execMdx",
                    contentType: "application/json; charset=UTF-8",
                    data: JSON.stringify({
                        mdx: txtArea.val()
                    }),
                    success: function (result) {
                        console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        console.log(result);
                        console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>>>>>>>>>>>>>>");
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