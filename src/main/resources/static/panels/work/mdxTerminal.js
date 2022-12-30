function MdxTerminalFactory() {

    MdxTerminalController.prototype.init = WW.commonSuperFunctions.init;

    function MdxTerminalController() {

        this.htmlTplPath = "/panels/work/mdxTerminal.html";

        this.doInit = function() {
            console.log("<<<<<<<<<<<<<<<<<<<<<<<< MdxTerminalController.doInit()");
        };
    }

    this.newController = function() {
        return new MdxTerminalController();
    };
};

WW.registerFactory(new MdxTerminalFactory(), "work/mdxTerminal");