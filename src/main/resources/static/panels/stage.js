function StageController() {
    this.init = function(div_id) {
        $.ajax({
            url: "/panels/stage.html",
            success: function(result) {
                $('#' + div_id).html(result);
            }
        });
    };
};

WW.register(new StageController(), "stage");