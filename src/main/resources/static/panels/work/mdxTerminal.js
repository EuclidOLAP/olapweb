function MdxTerminalFactory() {

    MdxTerminalController.prototype.init = WW.commonSuperFunctions.init;

    function MdxTerminalController() {

        this.htmlTplPath = "/panels/work/mdxTerminal.html";

        this.$txtArea;
        this.$result;
        this.$tbl;

        this.lastMultiDimsResult;

        this.doInit = function() {
            console.log("<<<<<<<<<<<<<<<<<<<<<<<< MdxTerminalController.doInit()");

            this.$txtArea = $('#' + this.divId + '_mdxTxtArea');
            this.$result = $('#' + this.divId + '_result');
            this.$tbl = $('#' + this.divId + '_tbl');

            this.$chartA_id = this.divId + '_chartA';
            this.$chartB_id = this.divId + '_chartB';

            this.$chartA = $('#' + this.$chartA_id);
            this.$chartB = $('#' + this.$chartB_id);

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
                    _$context: event.data,
                    success: function (data) {
//                        console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//                        console.log(result);
//                        console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>>>>>>>>>>>>>>");
                        this._$context.lastMultiDimsResult = JSON.parse(data.result);
                        console.log(this._$context.lastMultiDimsResult);

                        var formattedJson = JSON.stringify(this._$context.lastMultiDimsResult, null, "    ");
                        this._$context.$result.children().remove();
                        this._$context.$result
                            .append("<div style='font-size: 13px; white-space: pre; padding: 10px;'>" + formattedJson + "</div>");

                        this._$context.reDrawTable(this._$context.lastMultiDimsResult);

                        this._$context.$chartA.children().remove();
                        this._$context.$chartB.children().remove();
                        var tmpDivAId = WW.gn();
                        var tmpDivBId = WW.gn();
                        this._$context.$chartA.append("<div id='"+ tmpDivAId +"' style='width: 100%; height: 100%;'></div>");
                        this._$context.$chartB.append("<div id='"+ tmpDivBId +"' style='width: 100%; height: 100%;'></div>");

                        var multiDimsRst = this._$context.lastMultiDimsResult;
                        var chartCtlA = WW.findFactory("chart/chart").newController();
                        chartCtlA.init(tmpDivAId, function() {
                            chartCtlA.paint("basic_bar", multiDimsRst);
                        });
                        var chartCtlB = WW.findFactory("chart/chart").newController();
                        chartCtlB.init(tmpDivBId, function() {
                            chartCtlB.paint("smooth_line", multiDimsRst);
                        });
                    }
                });
            });
        };

        this.reDrawTable = function(mdResult) {
            this.$tbl.children().remove();
            if (mdResult.sets.length > 2) {
                this.$tbl.append("<tr><td>3D</td></tr>");
            } else if (mdResult.sets.length == 1) {
                for (var i = 0; i < mdResult.sets[0].ts.length; i++) {
                    var tuple = mdResult.sets[0].ts[i];
                    this.$tbl.append("<tr><td>" + tuple.display + "</td><td>" + mdResult.values[i] + "</td></tr>");
                }
            } else {
                // this.$tbl.append("<tr><td>2D</td></tr>");
                this.reDrawTable_2D(mdResult);
            }
        };

        this.reDrawTable_2D = function(mdResult) {
            var rowSet = mdResult.sets[0];
            var colSet = mdResult.sets[1];
            var rowLen = rowSet.ts.length;
            var colLen = colSet.ts.length;
            var rowTupLenMax = rowSet.tupLenMax;
            var colTupLenMax = colSet.tupLenMax;

            var tdMap = {};

            for (var i = 0; i < rowLen + colTupLenMax; i++) {
                var iRow = $("<tr></tr>");
                for (var j = 0; j < colLen + rowTupLenMax; j++) {
                    var pos = i+"_"+j;
                    var ijTd = $("<td _pos='" + pos + "'></td>");
                    iRow.append(ijTd);
                    tdMap[pos] = ijTd;
                }
                this.$tbl.append(iRow);
            }

            // column
            for (var i = 0; i < colLen; i++) {
                var tupleInfo = colSet.ts[i];
                for (var j = 0; j < tupleInfo.ms.length; j++) {
                    var memberInfo = tupleInfo.ms[j];
                    var pos = (j + colTupLenMax - tupleInfo.ms.length) + "_" + (rowTupLenMax + i);
                    tdMap[pos].html(memberInfo.display);
                }
            }

            // row
            for (var i = 0; i < rowLen; i++) {
                var tupleInfo = rowSet.ts[i];
                for (var j = 0; j < tupleInfo.ms.length; j++) {
                    var memberInfo = tupleInfo.ms[j];
                    var pos = (i + colTupLenMax) + "_" + (j + rowTupLenMax - tupleInfo.ms.length);
                    tdMap[pos].html(memberInfo.display);
                }
            }

            for (var i = 0; i < rowLen; i++) {
                for (var j = 0; j < colLen; j++) {
                    var pos = (i + colTupLenMax) + "_" + (j + rowTupLenMax);
                    tdMap[pos].html(mdResult.values[i * colLen + j]);
                }
            }
        };

    }

    this.newController = function() {
        return new MdxTerminalController();
    };
};

WW.registerFactory(new MdxTerminalFactory(), "work/mdxTerminal");