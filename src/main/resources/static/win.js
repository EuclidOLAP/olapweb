if (WW) {
    console.error("[OLAPWeb ERROR] Object 'WW' was existed.");
}

var WW = new function() {
    var _globalNum = 0;

    var controllers = {};
    var factories = {};

    this.register = function(controller, name) {
        if (controllers[name]) {
            console.error("[OLAPWeb ERROR] Controller '" + name + "' was existed.");
            return;
        }
        controllers[name] = controller;
    };

    this.registerFactory = function(factory, name) {
        if (factories[name]) {
            console.error("[OLAPWeb ERROR] Factory '" + name + "' was existed.");
            return;
        }
        factories[name] = factory;
    };

    this.findController = function(name) {
        return controllers[name];
    };

    this.findFactory = function(name) {
        return factories[name];
    };

    this.gn = function() {
        return ++_globalNum;
    };

    this.commonSuperFunctions = {
        init: function(div_id, callback) {
            this.divId = div_id;
            var _this = this;
            // load html
            $.ajax({
                url: this.htmlTplPath,
                ctlCallback: callback,
                success: function(result) {
                    $('#' + div_id).html(result.replace(/@PANEL@/g, div_id));
                    _this.doInit();
                    if (this.ctlCallback)
                        this.ctlCallback();
                }
            });
        }
    };
};