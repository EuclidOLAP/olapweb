function StageController() {
    this.init = function(div_id) {
        // load html
        $.ajax({
            url: "/panels/stage.html",
            success: function(result) {
                $('#' + div_id).html(result.replace(/@PANEL@/g, div_id));
                // resize
                var div_left = "#" + div_id + "_left" ;
                var div_star = "#" + div_id + "_star" ;
                var div_menu = "#" + div_id + "_menu" ;
                var div_right = "#" + div_id + "_right" ;
                var div_fns = "#" + div_id + "_fns" ;
                var div_work = "#" + div_id + "_work" ;
                $(div_right).width($("#" + div_id).width() - $(div_left).width());
                $(div_menu).height($(div_left).height() - $(div_star).height());
                $(div_work).height($(div_right).height() - $(div_fns).height());
            }
        });
    };
};

WW.register(new StageController(), "stage");