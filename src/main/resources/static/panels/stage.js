function StageController() {
    this.init = function(div_id) {
        $.ajax({
            url: "/panels/stage.html",
            success: function(result) {
                $('#' + div_id).html(result.replace(/@PANEL@/g, div_id));
            }
        });
    };
};

WW.register(new StageController(), "stage");