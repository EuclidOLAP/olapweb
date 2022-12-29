if (WW) {
    console.error("[OLAPWeb ERROR] Object 'WW' was existed.");
}

var WW = new function() {
    this.addController = function(controller) {
        console.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        console.log(controller);
        console.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    };
};