function MdxTerminalFactory() {

    function MdxTerminalController() {
        this.init = function(div_id) {
            var _this = this;
            // load html
            $.ajax({
                url: "/panels/work/mdxTerminal.html",
                success: function(result) {
                    $('#' + div_id).html(result.replace(/@PANEL@/g, div_id));
                    _this.doInit();
                }
            });
        };

        this.doInit = function() {
            console.log("<<<<<<<<<<<<<<<<<<<<<<<< MdxTerminalController.doInit()");
        };
    }

    this.newController = function() {
        return new MdxTerminalController();
    };
};

WW.registerFactory(new MdxTerminalFactory(), "work/mdxTerminal");