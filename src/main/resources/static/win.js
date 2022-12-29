if (WW) {
    console.error("[OLAPWeb ERROR] Object 'WW' was existed.");
}

var WW = new function() {
    var controllers = {};

    this.register = function(controller, name) {
        if (controllers[name]) {
            console.error("[OLAPWeb ERROR] Controller '" + name + "' was existed.");
            return;
        }
        controllers[name] = controller;
    };

    this.findController = function(name) {
        return controllers[name];
    };
};